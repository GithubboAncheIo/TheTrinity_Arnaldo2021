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

import java.util.ArrayDeque;

/**
 * @author Iannella Simone
 */
public class Player {
    private ArrayDeque<TamaGolem> team = new ArrayDeque<>();
    private String name;

    public Player(String name) {
        this.name= name;
    }

    public void homonymFix(){
        this.name = UsefulStrings.getHomonymName();
    }

    public void addTamaGolem(TamaGolem golem) {
        team.add(golem);
    }

    /**
     * This methods returns the {@code TamaGolem} golems that will
     * fight in the match.
     *
     * @return tamagolems.
     */
    public ArrayDeque<TamaGolem> getTeam() {
        return team;
    }

    public String getName() {
        return name;
    }

    public TamaGolem getActiveGolem() {
        TamaGolem g = team.peekFirst();
        if (g != null && !g.isAlive())
            team.removeFirst();
        return team.peekFirst();
    }

    public boolean isActiveGolemAlive() {
        return team.peekFirst() != null && team.peekFirst().isAlive();
    }

    public void setName(String name) {
        this.name = name;
    }
}
