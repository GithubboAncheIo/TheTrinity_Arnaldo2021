package it.unibs.fp.the_trinity.xml_utils;

import java.util.Arrays;

/**
 * This is the XMLTag's Class. We had to create it in order
 * to build the tags that are used in the Interfaces Parsable and
 * Writable. This Class is useful because, while we have to write
 * the output XML and to focus on the main task of this program,
 * the fiscal code's validation, we always need the tags, which
 * split out each part of fiscal code.
 *
 * @author Baresi Marco
 */
public class XMLTag {
    private String tagName;
    private String tagValue;
    private XMLAttribute[] attributes;


    // TODO comment
    public XMLTag(String tagName, String tagValue, XMLAttribute... attributes) {
        this.tagName = tagName;
        this.tagValue = tagValue;
        this.attributes = attributes;
    }

    // TODO comment
    public XMLTag(String tagName, XMLAttribute... attributes) {
        this(tagName, null, attributes);
    }

    /**
     * Constructor, it allows the creation of an object, that is XMLTag.
     *
     * @param tagName  represents the tag name.
     * @param tagValue represents the tag value.
     */
    public XMLTag(String tagName, String tagValue) {
        this(tagName, tagValue, new XMLAttribute[0]);
    }

    /**
     * Constructor, it allows the creation of an object, that is XMLTag.
     *
     * @param tagName * @param tagName represents the tag name.
     */
    public XMLTag(String tagName) {
        this(tagName, (String) null);
    }

    /**
     * @return a string that is tagName.
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * @param tagName, initialization.
     */
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    /**
     * @return a string that is tagValue.
     */
    public String getTagValue() {
        return tagValue;
    }

    /**
     * @param tagValue, initialization.
     */
    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    public XMLAttribute[] getAttributes() {
        return attributes;
    }

    @Override
    public String toString() {
        return "XMLTag{" +
                "tagName='" + tagName + '\'' +
                ", tagValue='" + tagValue + '\'' +
                ", attributes=" + Arrays.toString(attributes) +
                '}';
    }
}