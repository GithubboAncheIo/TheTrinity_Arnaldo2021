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

import it.unibs.fp.the_trinity.utilities.UsefulStrings;

import java.util.*;

public class FightHandler {
    private int elements;
    private int commonStones;
    private int tamas;
    private int golemStones;
    private Player playerA;
    private Player playerB;
    private ArrayList<TamaElement> usedElements;
    private HashMap<TamaElement, Integer> numberOfElementAndStones;

    public FightHandler(int matchLevel, Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
        elements = FightUtils.howManyElements(matchLevel);
        golemStones = FightUtils.howManyStones(elements);
        tamas = FightUtils.howManyTamagolems(elements, golemStones);
        commonStones = FightUtils.howManyCommonStones(tamas, elements, golemStones);
        usedElements = FightUtils.generateRandomElements(elements);
        numberOfElementAndStones = FightUtils.generateElementAndStonesMap(usedElements, FightUtils.howManyStonesForEachElement(tamas, elements, golemStones));
        addTamaGolems();
    }

    private void addTamaGolems() {
        for (int i = 0; i < tamas; i++) {
            String tamaName = UsefulStrings.TAMAGOLEM_NAME + i;
            TamaGolem t = new TamaGolem(FightUtils.ENERGY, tamaName);
            t.setName(tamaName);
            try {
                playerA.addTamaGolem((TamaGolem) t.clone());
                playerB.addTamaGolem((TamaGolem) t.clone());
            } catch (CloneNotSupportedException ignored) {}
        }
    }

    public int getElements() {
        return elements;
    }

    public int getCommonStones() {
        return commonStones;
    }

    public int getTamas() {
        return tamas;
    }

    public int getGolemStones() {
        return golemStones;
    }

    public Player getPlayerA() {
        return playerA;
    }

    public Player getPlayerB() {
        return playerB;
    }

    public ArrayList<TamaElement> getUsedElements() {
        return usedElements;
    }

    public HashMap<TamaElement, Integer> getNumberOfElementAndStones() {
        return numberOfElementAndStones;
    }

    public int remainingStonesForElement(TamaElement element) {
        return numberOfElementAndStones.get(element);
    }

    public void decreaseStonesOfElement(TamaElement element) {
        numberOfElementAndStones.replace(element, (numberOfElementAndStones.get(element)-1));
    }
}