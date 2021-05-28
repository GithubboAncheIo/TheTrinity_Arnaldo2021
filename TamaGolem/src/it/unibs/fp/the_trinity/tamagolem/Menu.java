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
import it.unibs.fp.the_trinity.utilities.DataInput;
import it.unibs.fp.the_trinity.utilities.Time;
import it.unibs.fp.the_trinity.utilities.UsefulStrings;

/**
 * The {@code Menu} class.
 *
 * @author Contestabile Martina
 * @author Iannella Simone
 */
public class Menu {
    private Player playerA;
    private Player playerB;

    public void menu() {
        int interaction;
        int newLife = 0;
        TamaElement elementA;
        TamaElement elementB;
        String golemAName;
        String golemBName;
        String winner;
        char answer;
        System.out.println(UsefulStrings.WELCOME_MESSAGE);
        System.out.println(UsefulStrings.DOUBLE_LINE);
        Time.pause(Time.HIGH_MILLIS_PAUSE);
        boolean end = false;

        do {
            setPlayersNames();
            System.out.println(UsefulStrings.FRAME);
            int matchLevel = DataInput.readIntWithMaxAndMin(UsefulStrings.SELECT_LEVEL, FightUtils.EASY_LEVEL, FightUtils.HARD_LEVEL);
            FightHandler fightHandler = new FightHandler(matchLevel, playerA, playerB);

            Equilibrium equilibrium = new Equilibrium(FightUtils.ENERGY, fightHandler.getElements());

            Time.pause(Time.LOW_MILLIS_PAUSE);
            System.out.println(UsefulStrings.DOUBLE_LINE);
            System.out.printf(UsefulStrings.HOW_MANY_TAMAGOLEMS, fightHandler.getTamas());
            System.out.printf(UsefulStrings.HOW_MANY_ELEMENTS, fightHandler.getElements());
            System.out.printf(UsefulStrings.HOW_MANY_STONES, fightHandler.getGolemStones());
            Time.pause(Time.LOW_MILLIS_PAUSE);
            System.out.println(UsefulStrings.getStartFightMessage(playerA.getName(), playerB.getName(), fightHandler.getTamas(), FightUtils.ENERGY, fightHandler.getGolemStones()));
            Time.pause(Time.HIGH_MILLIS_PAUSE);

            do {
                fightHandler.getPlayerA().getActiveGolem().addElements(chooseGolemStones(fightHandler, playerA));
                fightHandler.getPlayerB().getActiveGolem().addElements(chooseGolemStones(fightHandler, playerB));
                do {
                    golemAName = playerA.getActiveGolem().getName();
                    golemBName = playerB.getActiveGolem().getName();
                    elementA = fightHandler.getPlayerA().getActiveGolem().nextElement();
                    elementB = fightHandler.getPlayerB().getActiveGolem().nextElement();
                    interaction = equilibrium.calculateInteraction(fightHandler.getUsedElements().indexOf(elementA), fightHandler.getUsedElements().indexOf(elementB));
                    System.out.println(UsefulStrings.FRAME + "\n" + UsefulStrings.getElementsVs(elementA.toString(), elementB.toString()));
                    if (interaction < 0) {
                        System.out.println(UsefulStrings.getTamagolemDamage(playerB.getName(), -interaction, playerA.getName()));
                        newLife = fightHandler.getPlayerA().getActiveGolem().damageGolem(-interaction);
                        if (newLife > 0)
                            System.out.println(UsefulStrings.getTamagolemLife(golemAName, playerA.getName(), newLife, FightUtils.ENERGY));
                    } else if (interaction > 0) {
                        System.out.println(UsefulStrings.getTamagolemDamage(playerA.getName(), interaction, playerB.getName()));
                        newLife = fightHandler.getPlayerB().getActiveGolem().damageGolem(interaction);
                        if (newLife > 0)
                            System.out.println(UsefulStrings.getTamagolemLife(golemBName, playerB.getName(), newLife, FightUtils.ENERGY));
                    } else
                        System.out.println(UsefulStrings.NULL_INTERACTION);
                    Time.pause(Time.HIGH_MILLIS_PAUSE);
                } while (fightHandler.getPlayerA().isActiveGolemAlive() && fightHandler.getPlayerB().isActiveGolemAlive());
                System.out.printf(UsefulStrings.getDeathMessage(), fightHandler.getPlayerA().isActiveGolemAlive() ? golemBName : golemAName);
                System.out.println(UsefulStrings.getCondolenceMessage());
            } while (fightHandler.getPlayerA().getActiveGolem() != null && fightHandler.getPlayerB().getActiveGolem() != null);
            if (fightHandler.getPlayerA().getActiveGolem() != null)
                winner = fightHandler.getPlayerA().getName();
            else
                winner = fightHandler.getPlayerB().getName();
            System.out.println(UsefulStrings.getWinner(winner));
            answer = DataInput.readChar(UsefulStrings.END_QUESTION);
            if (answer != 'S' && answer != 's')
                end = true;
        } while (!end);
        System.out.println(UsefulStrings.DOUBLE_LINE);
        System.out.println(UsefulStrings.getGoodbyeString());
    }

    // TODO add comments
    private void setPlayersNames() {
        char homonymAnswer;
        playerA = new Player(DataInput.readNotEmptyString(UsefulStrings.PLAYER_1_NAME_REQUEST));
        playerB = new Player(DataInput.readNotEmptyString(UsefulStrings.PLAYER_2_NAME_REQUEST));
        if (playerA.getName().equalsIgnoreCase(playerB.getName())) {
            homonymAnswer = DataInput.readChar(UsefulStrings.HOMONYM_MESSAGE);
            if (homonymAnswer == 'S' || homonymAnswer == 's') {
                String tmp = playerB.getName();
                do {
                    playerB.homonymFix();
                } while (playerB.getName().equals(tmp));
                System.out.println(UsefulStrings.HOMONYM_FIXED_MESSAGE + playerB.getName() + "\".");
            } else {
                String p1Name = playerA.getName();
                do {
                    playerB.setName(DataInput.readNotEmptyString(UsefulStrings.player2SecondNameRequest(playerA.getName())));
                } while ((playerB.getName().equalsIgnoreCase(p1Name)));
            }
        }
        Time.pause(Time.LOW_MILLIS_PAUSE);
    }

    /**
     * This method returns the elements used during the fight.
     *
     * @return usedElements.
     */
    /*
    public ArrayList<TamaElement> getUsedElements() {
        return usedElements;
    }
     */

    public CircularList<TamaElement> chooseGolemStones(FightHandler fightHandler, Player player) {
        CircularList<TamaElement> chosenStones = new CircularList<>();
        System.out.println(UsefulStrings.DOUBLE_LINE);
        System.out.printf(UsefulStrings.SETTING_ELEMENTS, player.getName());
        TamaElement element = null;
        String choice;
        int golemStones = fightHandler.getGolemStones();

        while (golemStones > 0) {
            // TODO migliora la stampa degli elementi
            for (TamaElement e : fightHandler.getNumberOfElementAndStones().keySet())
                if (fightHandler.remainingStonesForElement(e) > 0)
                    System.out.println("-" + e.toString());
            System.out.println();
            boolean error = false;
            do {
                element = null;
                if (error)
                    System.out.println(UsefulStrings.getErrorString());
                choice = DataInput.readNotEmptyString(UsefulStrings.CHOOSE_ELEMENT_NAME).toUpperCase();
                TamaElement t = TamaElement.getElementFromAbbreviation(fightHandler.getUsedElements(), choice);

                if (t != null) {
                    if (fightHandler.remainingStonesForElement(t) <= 0)
                        error = true;
                    else
                        element = t;
                } else if (TamaElement.containsElement(choice) && fightHandler.getUsedElements().contains(TamaElement.valueOf(choice))) {
                    element = TamaElement.valueOf(choice);
                } else
                    error = true;

            } while (element == null);

            chosenStones.add(element);
            fightHandler.decreaseStonesOfElement(element);

            golemStones--;
        }

        return chosenStones;
    }


}

