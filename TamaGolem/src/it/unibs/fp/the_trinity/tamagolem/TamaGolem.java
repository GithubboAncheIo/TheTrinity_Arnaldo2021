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

import it.unibs.fp.the_trinity.utilities.CircularList;

import java.util.ArrayList;

public class TamaGolem implements Cloneable {
    private int energy;
    private CircularList<TamaElement> elements;
    private String name;
    private boolean alive;

    public TamaGolem(int energy, String name) {
        this.energy = energy;
        this.name = name;
        alive = true;
    }

    public void addElements(CircularList<TamaElement> elements) {
        this.elements = elements;
    }

    public int getEnergy() {
        return energy;
    }

    public ArrayList<TamaElement> getElements() {
        return elements;
    }

    public TamaElement nextElement() {
        return elements.getNext();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param damage inflicted
     * @return new life of golem
     */
    public int damageGolem(int damage) {
        energy -= damage;
        if (energy <= 0)
            alive = false;
        return energy;
    }

    public boolean isAlive() {
        return alive;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
