package it.unibs.fp.the_trinity.xml_utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Supplier;

/**
 * This Interface allows the writing of the .xml file.
 *
 * @author Baresi Marco
 */
public interface Writable {
    MultiMap<String, Supplier<String>> getters = new MultiMap<>();

    /**
     * We are setting the attributes of the output .xml file.
     *
     * @return the tags we want to put in the output .xml file.
     */
    default ArrayList<XMLAttribute> getAttributesToWrite() {
        setGetters();
        ArrayList<XMLAttribute> attributes = new ArrayList<>();
        for (String name : getAttributeStrings()) {
            for (Supplier<String> getter : getters.get(name))
                attributes.add(new XMLAttribute(name, getter.get()));
        }
        return attributes;
    }

    /**
     * We are setting the child tags of the output .xml file.
     *
     * @return the tags we want to put in the output .xml file.
     */
    default ArrayList<XMLTag> getChildTagsToWrite() {
        setGetters();
        ArrayList<XMLTag> XMLTags = new ArrayList<>();
        for (String name : getChildTagStrings()) {
            for (Supplier<String> getter : getters.get(name))
                XMLTags.add(new XMLTag(name, getter.get()));
        }
        return XMLTags;
    }

    //Getters and Setters
    void setGetters();

    String getTagName();

    String[] getAttributeStrings();

    String[] getChildTagStrings();
}