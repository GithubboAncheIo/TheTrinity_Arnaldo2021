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

import java.util.ArrayList;
import java.util.Arrays;

public enum TamaElement {
    ASFALTO("A"),
    SINDONE_DI_TORINO("S"),
    HENRICOBACTER("H"),
    CADREGA("C"),
    MEMORY_FOAM("M"),
    LIQUORE_DI_ZEKE("L"),
    PAPILLOMA("P"),
    ERBA("E"),
    TAPPETO_DI_KEYSHAN("T"),
    DOGECOIN("D");

    private String abbreviation;

    TamaElement(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public boolean containsAbbreviation(String value) {
        return abbreviation.equals(value.toUpperCase());
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public static boolean containsElement(String value) {
        return Arrays.stream(TamaElement.class.getEnumConstants()).anyMatch(e -> e.name().equals(value));
    }

    public static TamaElement getElementFromAbbreviation(ArrayList<TamaElement> elements, String abbreviation) {
        for (TamaElement t : elements)
            if (t.containsAbbreviation(abbreviation)) return t;
        return null;
    }
}
