package it.unibs.fp.the_trinity.planetarium;

import java.util.ArrayList;
import java.util.HashMap;

public class Star extends CelestialBody {
	public static final int MAX_PLANETS = 26000;
	private HashMap<String, Planet> planets;

	// permette di istanziare un oggetto di tipo Star
	public Star(double mass, String code) {
		super(mass, new Coordinates(0, 0), code, CelestialBody.STAR_CODE);
		planets = new HashMap<>();
	}


	// permette di aggiungere pianeti alla stella
	public boolean addPlanet(Planet planet) {
		if (planets.size() < MAX_PLANETS) {
			planets.put(planet.getCode(), planet);
			return true;
		}
		return false;
	}

	// permette la rimozione di pianeti compromessi da catastrofi naturali
	public Planet removePlanet(String planetCode) {
		return planets.remove(UsefulStrings.capitalize(planetCode));
	}

	// verifica se il planetCode e' associato ad un pianeta del sole
	public boolean containsPlanet(String planetCode) {
		return planets.containsKey(UsefulStrings.capitalize(planetCode));
	}

	// restituisce il pianeta associato al planetCode
	public Planet getPlanet(String planetCode) {
		return planets.get(UsefulStrings.capitalize(planetCode));
	}

	// restituisce un HashMap contenente i pianeti che orbitano attorno alla stella
	public HashMap<String, Planet> getPlanets() {
		return planets;
	}

	// restituisce un ArrayList contenente i pianeti che orbitano attorno alla stella
	public ArrayList<Planet> getPlanetsArrayList() {
		return new ArrayList<Planet>(planets.values());
	}

	// restituisce true se sono presenti pianeti all'interno dell'orbita del sole, false altrimenti
	public boolean hasPlanets() {
		return (planets.size() != 0);
	}

	// restituisce il numero di pianeti presenti nel sistema stellare
	public int getPlanetsNumber() {
		return planets.size();
	}

	// controlla se uno dei pianeti della stella contiene la luna associata al moonCode
	public boolean containsMoon(String moonCode) {
		boolean isContained = false;
		for (Planet planet : planets.values()) {
			if (planet.containsMoon(UsefulStrings.capitalize(moonCode))) {
				isContained = true;
				break;
			}
		}
		return isContained;
	}

	// restituisce il path necessario per raggiungere la luna associata al moonCode
	public String getMoonPath(String moonCode) {
		moonCode = UsefulStrings.capitalize(moonCode);
		String moonPath = "";

		for (Planet planet : planets.values())
			if(planet.containsMoon(moonCode))
				moonPath = getCode() + UsefulStrings.SEPARATOR + planet.getCode() + UsefulStrings.SEPARATOR + moonCode;

		return moonPath;
	}
}
