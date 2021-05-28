package it.unibs.fp.the_trinity.xml_utils;

public class XMLAttribute {
    private String name;
    private String value;

    public XMLAttribute(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
