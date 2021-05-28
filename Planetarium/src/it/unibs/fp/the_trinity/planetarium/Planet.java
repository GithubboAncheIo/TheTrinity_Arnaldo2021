package it.unibs.fp.the_trinity.planetarium;

import java.util.ArrayList;
import java.util.HashMap;

public class Planet extends CelestialBody {
	public static final int MAX_MOONS = 5000;
	private HashMap<String, Moon> moons;
	private String starCode;

	// permette di istanziare un oggetto di tipo Planet
	public Planet(double mass, Coordinates coords, String code, String starCode) {
		super(mass, coords, code, CelestialBody.PLANET_CODE);
		this.starCode = UsefulStrings.capitalize(starCode);
		moons = new HashMap<String, Moon>();
	}


	// permette l'inserimento di nuove lune
	public boolean addMoon(Moon moon) {
		if (moons.size() < MAX_MOONS) {
			moons.put(moon.getCode(), moon);
			return true;
		}
		return false;
	}

	// permette la rimozione di lune compromesse da catastrofi naturali
	public Moon removeMoon(String moonCode) {
		return moons.remove(UsefulStrings.capitalize(moonCode));
	}

	// verifica se il moonCode e' associato ad una luna del pianeta
	public boolean containsMoon(String moonCode) {
		return moons.containsKey(UsefulStrings.capitalize(moonCode));
	}

	// restituisce l'oggetto di tipo luna associato al moonCode
	public Moon getMoon(String moonCode) {
		return moons.get(UsefulStrings.capitalize(moonCode));
	}

	// restituisce un HashMap contenente le lune che orbitano attorno al pianeta
	public HashMap<String, Moon> getMoons() {
		return moons;
	}

	// restituisce true se sono presenti lune all'interno dell'orbita del pianeta, false altrimenti
	public boolean hasMoon() {
		return (moons.size() != 0);
	}

	// restituisce un HashMap contenente le lune che orbitano attorno al pianeta
	public ArrayList<Moon> getMoonsArrayList() {
		return new ArrayList<Moon>(moons.values());
	}

	// restituisce il codice del sole attorno a cui orbita
	public String getStarCode() {
		return starCode;
	}
}