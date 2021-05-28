package it.unibs.fp.the_trinity.planetarium;

public abstract class CelestialBody {
	private double mass;
	private Coordinates coords;
	private String code;
	public final static int STAR_CODE = 1;
	public final static int PLANET_CODE = 2;
	public final static int MOON_CODE = 3;
	public final int MY_TYPE_CODE;

	// permette di istanziare un oggetto di tipo corpo celeste
	public CelestialBody(double mass, Coordinates coords, String code, final int MY_TYPE_CODE) {
		this.mass = mass;
		this.coords = coords;
		this.code = UsefulStrings.capitalize(code);
		this.MY_TYPE_CODE = MY_TYPE_CODE;
	}

	// restituisce il valore della massa
	public double getMass() {
		return mass;
	}

	// permette di modificare il valore della massa
	public void setMass(double mass) {
		this.mass = mass;
	}

	// restituisce il valore delle coordinate
	public Coordinates getCoords() {
		return coords;	}

	// permette di modificare il valore delle coordinate
	public void setCoords(Coordinates coords) {
		this.coords = coords;
	}

	// restituisce il codice univoco del corpo celeste
	public String getCode() {
		return code;
	}
}
