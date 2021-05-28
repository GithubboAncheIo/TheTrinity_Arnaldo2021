package it.unibs.fp.the_trinity.xml_utils;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * This Interface allows the process of
 * writing of the .xml file.
 *
 * @author Baresi Marco
 */
public class XMLWriter {
    private XMLStreamWriter xmlWriter = null;
    public final static String ENCODING = "utf-8";
    public final static String VERSION = "1.0";
    public static int tabLevel = 0;
    public static final int INCREMENT_LEVEL = 1;
    public static final int SAME_LEVEL = 0;
    public static final int DECREMENT_LEVEL = -1;

    /**
     * It generates a file .xml that has a name
     * that is a String. The name is given
     * by the user. As the exercise's text
     * says, we have to call it outputPersone.
     *
     * @param fileName contains the xml output name.
     */
    public XMLWriter(String fileName) {
        try {
            XMLOutputFactory xmlFactory = XMLOutputFactory.newInstance();
            xmlWriter = xmlFactory.createXMLStreamWriter(new FileOutputStream(fileName), ENCODING);
            xmlWriter.writeStartDocument(ENCODING, VERSION);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the opening Tag of fileName.xml.
     *
     * @param openingTag names of opening tag
     */
    public void writeOpeningTagXML(String openingTag) {
        try {
            writeTabs(INCREMENT_LEVEL);
            xmlWriter.writeStartElement(openingTag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method closes an open tag.
     *
     * @param closeDocument if true, it close document
     *                      of the fileName.xml.
     */
    public void writeClosingTagXML(boolean closeDocument) {
        try {
            writeTabs(DECREMENT_LEVEL);
            xmlWriter.writeEndElement();
            if (closeDocument) {
                xmlWriter.writeEndDocument();
                xmlWriter.flush();
                xmlWriter.close();
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method writes the Object that we need in
     * the file .xml fileName.xml.
     *
     * @param obj represents a generic object.
     * @param <T> represents a generic type.
     */
    public <T extends Writable> void writeObjectXML(T obj) {
        ArrayList<XMLTag> elements;
        ArrayList<XMLAttribute> attributes;
        String startTagName;
        boolean emptyElement = false;
        boolean emptyTag;
        boolean singleAttribute = false;

        try {
            startTagName = obj.getTagName();
            attributes = obj.getAttributesToWrite();
            elements = obj.getChildTagsToWrite();

            writeTabs(INCREMENT_LEVEL);

            XMLTag tag = new XMLTag(startTagName, attributes.toArray(new XMLAttribute[0]));
            if (elements == null || elements.size() == 0) emptyElement = true;
            writeTag(tag, emptyElement);

            if (elements != null) {
                for (XMLTag t : elements) {
                    emptyTag = false;
                    if (t.getTagName().equals(startTagName) && elements.size() == 1)
                        singleAttribute = true;
                    if (t.getTagValue() == null)
                        emptyTag = true;
                    if (!singleAttribute) {
                        writeTabs(SAME_LEVEL);
                        writeTag(t, emptyTag);
                    }
                    xmlWriter.writeCharacters(t.getTagValue());
                    if (!singleAttribute && !emptyTag) xmlWriter.writeEndElement();
                }
            }
            if (!singleAttribute) writeTabs(DECREMENT_LEVEL);
            else tabLevel--;
            if (!emptyElement) xmlWriter.writeEndElement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method writes the ArrayList that we need in
     * the file .xml fileName.xml, considering the parameters
     * we would like to add in the ArrayList.
     *
     * @param objList        represents a generic object ArrayList.
     * @param arrayName      represents the tag name of the array.
     * @param arrayAttribute represents the array's attributes.
     * @param attributeValue represents the array's attributes.
     * @param openingTag     document's opening tag.
     * @param <T>            represents a generic type.
     */
    public <T extends Writable> void writeArrayListXML(ArrayList<T> objList, String arrayName, String arrayAttribute, String attributeValue, String openingTag) {
        try {
            if (openingTag != null) writeOpeningTagXML(openingTag);
            if (arrayName != null) {
                writeTabs(INCREMENT_LEVEL);
                xmlWriter.writeStartElement(arrayName);
                if (arrayAttribute != null) xmlWriter.writeAttribute(arrayAttribute, attributeValue);
            }

            for (T obj : objList)
                writeObjectXML(obj);

            if (arrayName != null) {
                writeTabs(DECREMENT_LEVEL);
                xmlWriter.writeEndElement();
            }
            if (openingTag != null) writeClosingTagXML(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method writes the ArrayList that we need in
     * the file .xml fileName.xml, considering the parameters
     * we would like to add in the ArrayList.
     */
    public <T extends Writable> void writeArrayListXML(ArrayList<T> objList, String arrayName, String arrayAttribute, String attributeValue) {
        writeArrayListXML(objList, arrayName, arrayAttribute, attributeValue, null);
    }

    /**
     * This method writes the ArrayList that we need in
     * the file .xml fileName.xml, considering the parameters
     * we would like to add in the ArrayList.
     */
    public <T extends Writable> void writeArrayListXML(ArrayList<T> objList, String arrayName) {
        writeArrayListXML(objList, arrayName, null, null, null);
    }

    /**
     * This method writes the ArrayList that we need in
     * the file .xml fileName.xml, considering the parameters
     * we would like to add in teh ArrayList.
     */
    public <T extends Writable> void writeArrayListXML(ArrayList<T> objList) {
        writeArrayListXML(objList, null, null, null);
    }

    private void writeTag(XMLTag t, boolean emptyTag) throws XMLStreamException {
        if (emptyTag)
            xmlWriter.writeEmptyElement(t.getTagName());
        else
            xmlWriter.writeStartElement(t.getTagName());

        for (XMLAttribute a : t.getAttributes())
            xmlWriter.writeAttribute(a.getName(), a.getValue());
    }

    /**
     * @param changeLevel if 1, it increases tabLevel (number of tabs)
     *                    if -1, it decreases tabLevel
     * @throws XMLStreamException managed by others methods.
     */
    private void writeTabs(int changeLevel) throws XMLStreamException {
        if (changeLevel == DECREMENT_LEVEL) tabLevel--;
        xmlWriter.writeCharacters("\n");
        for (int i = 0; i < tabLevel; i++) {
            xmlWriter.writeCharacters("\t");
        }
        if (changeLevel == INCREMENT_LEVEL) tabLevel++;
    }
}