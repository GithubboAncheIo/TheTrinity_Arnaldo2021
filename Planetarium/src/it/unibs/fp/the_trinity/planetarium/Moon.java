package it.unibs.fp.the_trinity.planetarium;

public class Moon extends CelestialBody {
	private String planetCode;

	// permette di istanziare un oggetto di tipo Moon
	public Moon(double mass, Coordinates coords, String code, String planetCode) {
		super(mass, coords, code, CelestialBody.MOON_CODE);
		this.planetCode = UsefulStrings.capitalize(planetCode);
	}

	// restituisce il codice del pianeta attorno a cui orbita
	public String getPlanetCode() {
		return planetCode;
	}
}
