package it.unibs.fp.the_trinity.objects;

import it.unibs.fp.the_trinity.xml_utils.Writable;
import it.unibs.fp.the_trinity.xml_utils.XMLAttribute;
import it.unibs.fp.the_trinity.xml_utils.XMLTag;

import java.util.ArrayList;

/**
 * This class is used to store {@code Route} of a team.
 *
 * @author Baresi Marco
 */
public class Route implements Writable {
    private String team;
    private String cost;
    private String cities;
    private ArrayList<City> citiesList;
    public static final String START_STRING = "route";
    private static final ArrayList<String> ATTRIBUTE_STRINGS = new ArrayList<>();

    /*
     * The keyword static is used to create methods that will exist independently
     * of any instances created for the class. Static methods do not use
     * any instance variables of any object of the class they are defined in.
     */
    static {
        ATTRIBUTE_STRINGS.add("team");
        ATTRIBUTE_STRINGS.add("cost");
        ATTRIBUTE_STRINGS.add("cities");
    }

    /**
     * {@code Route} constructor.
     */
    public Route(String team, String cost, String cities, ArrayList<City> citiesList) {
        this.team = team;
        this.cost = cost;
        this.cities = cities;
        this.citiesList = citiesList;
    }

    @Override
    public void setGetters() {
        getters.clear();
        getters.put(ATTRIBUTE_STRINGS.get(0), this::getTeam);
        getters.put(ATTRIBUTE_STRINGS.get(1), this::getCost);
        getters.put(ATTRIBUTE_STRINGS.get(2), this::getCities);
    }

    @Override
    public String getTagName() {
        return START_STRING;
        // return new XMLTag(START_STRING, teamAttribute, costAttribute, citiesAttribute);
    }

    @Override
    public String[] getAttributeStrings() {
        return ATTRIBUTE_STRINGS.toArray(new String[0]);
    }

    @Override
    public String[] getChildTagStrings() {
        return new String[]{City.START_STRING};
    }

    public String getTeam() {
        return team;
    }

    public String getCost() {
        return cost;
    }

    public String getCities() {
        return cities;
    }

    @Override
    public ArrayList<XMLTag> getChildTagsToWrite() {
        setGetters();
        ArrayList<XMLTag> XMLTags = new ArrayList<>();

        for (City c : citiesList)
            XMLTags.add(new XMLTag(c.getStartString(), new XMLAttribute("id", c.getId()), new XMLAttribute("name", c.getName())));

        return XMLTags;
    }
}
