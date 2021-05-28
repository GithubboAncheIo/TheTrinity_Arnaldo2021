package it.unibs.fp.the_trinity.utilities;

import java.util.ArrayList;

public class CircularList<E> extends ArrayList<E> {
    private int index = 0;

    public E getNext() {
        if (index == -1)
            index = size()-1;
        else if (index == size())
            index = 0;
        return super.get(index++);
    }
}