package it.unibs.fp.the_trinity.xml_utils;

import java.util.HashMap;
import java.util.function.Consumer;

/**
 * This Interface allows the parsing of the .xml file.
 *
 * @author Baresi Marco
 */
public interface Parsable {
    HashMap<String, Consumer<String>> setters = new HashMap<>();

    /**
     * This method gets the attributes and tags from our input file.
     *
     * @param xmlTag represents the tag of an .xml file.
     */
    default void setAttribute(XMLTag xmlTag) {
        setSetters();
        Consumer<String> method = setters.get(xmlTag.getTagName());
        if (method != null) method.accept(xmlTag.getTagValue());
        XMLAttribute[] attributes = xmlTag.getAttributes();
        if (attributes != null) {
            for (XMLAttribute a : attributes) {
                method = setters.get(a.getName());
                if (method != null) method.accept(a.getValue());
            }
        }
    }

    /**
     * Indicates if {@link #setters} contains a specific tag.
     *
     * @param tag represent the the tag of an .xml file.
     * @return if there's a setter associated with the tag name or not.
     */
    default boolean containsAttribute(String tag) {
        return setters.get(tag) != null;
    }

    /**
     * Method needed to define {@link #setters} used to set attrivutes readed
     * from the parser.
     * <p>
     * Implementation method example:
     * public void setSetters() {
     * setters.put(ATTRIBUTE_STRINGS.get(0), this::setAttribute0);
     * ...
     * }
     */
    void setSetters();

    /**
     * Returns the String value of the first tag name.
     */
    String getStartString();
}