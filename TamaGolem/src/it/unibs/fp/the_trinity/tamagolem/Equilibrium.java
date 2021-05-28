/*
 * Copyright (c) 2021, The Trinity and/or its affiliates. All rights reserved.
 * THE TRINITY PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package it.unibs.fp.the_trinity.tamagolem;

import java.util.Random;

/**
 * The {@code Equilibrium} class contains methods to generate the
 * equilibrium for the TamaGolem game. It must be instantiated.
 *
 * @author Baresi Marco
 */
public class Equilibrium {
    private final int maxPower;
    private int nElements;
    private int[][] matrix;

    /**
     * The {@code int} value used as parameter for invoking properly
     * {@link #swapCells(int, int, int, int, int)} when swapping cells
     * is required if the second cell has a lower value than first.
     */
    public static final int IF_LOWER = -1;

    /**
     * The {@code int} value used as parameter for invoking properly
     * {@link #swapCells(int, int, int, int, int)} when swapping cells
     * is required in each case.
     */
    public static final int EACH_CASE = 0;

    /**
     * The {@code int} value used as parameter for invoking properly
     * {@link #swapCells(int, int, int, int, int)} when swapping cells
     * is required if the second cell has a higher value than first.
     */
    public static final int IF_HIGHER = 1;

    /**
     * Constructs a newly allocated {@code Equilibrium} object that
     * generate the equilibrium matrix, through {@link #generateMatrix()}
     * method.
     * It is necessary to use this constructor to create TamaGolem Equilibrium.
     *
     *
     * @param   maxPower    the highest value that each matrix cell can take.
     * @param   nElements   number of elements representing the size of the
     *                      equilibrium matrix.
     *
     */
    public Equilibrium(int maxPower, int nElements) {
        this.maxPower = maxPower;
        this.nElements = nElements;
        generateMatrix();
    }

    /**
     * Generates equilibrium {@link #matrix}, considerating that {@link #maxPower}
     * represents maximum random value of the cell (and -{@link #maxPower} the minimum);
     * number of {@code columns} and {@code rows} are represented by the number of
     * TamaGolem elements.
     */
    private void generateMatrix() {
        matrix = new int[nElements][nElements];
        int rowSum, value=0, min, max, remainingCols, e = 0;

        for (int r = 0; r < (nElements-1); r++) {
            rowSum = 0;
            for (int c = 0; c < nElements; c++) {
                // If on the main diagonal it must remain with the value 0.
                if (c == r) continue;

                // If before main diagonal and the middle of the row,
                // cell assumes random value between 1 and maxPower.
                if ((c + 1) <= (nElements / 2) && c > r) value = randomInteger(1, maxPower);

                    // if after main diagonal and before the last position of the row,
                    // cell assumes pseudo-random value between "min" and "max".
                else if (c != (nElements - 1) && c > r) {
                    remainingCols = nElements - (c + 1);

                    // Min and max defines range of the pseudo-random value
                    min = Math.max(Math.min(remainingCols * (-maxPower) - rowSum, maxPower), -maxPower);
                    max = Math.min(Math.max(remainingCols * (maxPower) - rowSum, -maxPower), maxPower);

                    do {
                        // Pseudo-random value must be different from "0" (and also from "-rowSum"
                        // if it will represent the value of the second to last cell of the row, to
                        // prevent the last cell from taking the value 0).
                        value = randomInteger(min, max, (c == (nElements - 2)) ? new int[]{-rowSum, 0} : new int[]{0});
                    } while (min != max && (remainingCols * maxPower + rowSum + value) == 0);
                }

                else if (c == (nElements - 1)) {
                    e = 0;
                    while (rowSum == 0 || rowSum < -maxPower || rowSum > maxPower) {
                        e++;
                        // If the last cell of the row will take an illegal value,
                        // the entire line is scanned and associated values are
                        // swapped if useful.
                        for (int col = 0; col < r; col++) {
                            swapCells(col, r, col, nElements - 1, (rowSum == 0 ? EACH_CASE : (rowSum > maxPower ? IF_LOWER : IF_HIGHER)));
                            rowSum = 0;
                            for (int i = 0; i < (nElements - 1); i++) rowSum += matrix[r][i];
                            if (rowSum >= -maxPower && rowSum <= maxPower && rowSum != 0) break;
                        }

                        if (e == 3) {
                            e = -1;
                            break;
                        }
                    }
                    value = -rowSum;
                }

                if (c > r) {
                    matrix[r][c] = value;
                    matrix[c][r] = -value;
                }

                rowSum += matrix[r][c];

                if (e == -1) {
                    generateMatrix();
                    break;
                }

            }
        }
    }

    /**
     * Generates a pseudorandom integer in the range [min, max].
     *
     * @param   min   the starting value of the range (inclusive)
     * @param   max   the ending value of the range (inclusive)
     * @param   exclude   all values that must be excluded from
     *                    this method
     * @return the Integer {@code random} value
     */
    private int randomInteger(int min, int max, int... exclude) {
        int random;
        boolean end;
        do {
            end = true;
            random = new Random().nextInt((Math.max(min, max)) - Math.min(min, max) + 1) + Math.min(min, max);
            for (int n : exclude)
                if (random == n) {
                    end = false;
                    break;
                }
        } while (!end);
        return random;
    }

    /**
     * Performs a swap between two cells of the {@link #matrix} if {@code CONDITION} is
     * satisfied.
     *
     * @param   rowA   row of the first cell
     * @param   colA   column of the first cell
     * @param   rowB   row of the second cell
     * @param   colB   column of the second cell
     * @param   CONDITION   assumes three possible value:
     *                      <ul>
     *                          <li>{@link #IF_LOWER}</li>
     *                          <li>{@link #EACH_CASE}</li>
     *                          <li>{@link #IF_HIGHER}</li>
     *                      </ul>
     */
    private void swapCells(int rowA, int colA, int rowB, int colB, final int CONDITION) {
        boolean swap = false;
        int firstValue = matrix[rowA][colA];
        int secondValue = matrix[rowB][colB];

        switch (CONDITION) {
            case IF_LOWER -> {
                if (firstValue < secondValue) swap = true;
            }

            // Change cell values if allowed.
            case EACH_CASE -> {
                if (firstValue > -maxPower && secondValue < maxPower) {
                    matrix[rowA][colA]--;
                    matrix[rowB][colB]++;
                } else if (firstValue < maxPower && secondValue > -maxPower) {
                    matrix[rowA][colA]++;
                    matrix[rowB][colB]--;
                }

                matrix[colA][rowA] = -matrix[rowA][colA];
                matrix[colB][rowB] = -matrix[rowB][colB];
            }

            case IF_HIGHER -> {
                if (matrix[rowA][colA] > matrix[rowB][colB]) swap = true;
            }
        }

        // Swap cell values.
        if (swap) {
            int t = matrix[rowA][colA];
            matrix[rowA][colA] = matrix[rowB][colB];
            matrix[rowB][colB] = t;

            matrix[colA][rowA] = -matrix[rowA][colA];
            matrix[colB][rowB] = -matrix[rowB][colB];
        }
    }

    /**
     * Returns the equilibrium matrix generated by {@link #generateMatrix()}.
     *
     * @return entire equilibrium matrix
     */
    public int[][] getEquilibriumMatrix() {
        return matrix;
    }

    /**
     * Returns the number of elements, that's equivalent to number of rows and
     * columns of the equilibrium {@link #matrix}.
     *
     * @return number of elements
     */
    public int getnElements() {
        return nElements;
    }

    // TODO create getDamage method
    public int calculateInteraction(int indexA, int indexB) {
        return matrix[indexA][indexB];
    }
}