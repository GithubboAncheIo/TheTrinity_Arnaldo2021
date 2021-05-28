package it.unibs.fp.the_trinity.planetarium;

import it.unibs.fp.the_trinity.utilities.DataInput;

public class Coordinates {
	private static final int SQUARE = 2;
	private double x;
	private double y;

	// metodo costruttore, ci permette di instanziare un oggetto di tipo Coordinates
	public Coordinates (double x, double y) {
		this.x = x;
		this.y = y;
	}

	/*
	 * Permette all'utente di creare un nuovo oggetto di tipo Coordinates inserirendo
	 * le coordinate da console.
	 */
	public static Coordinates makeCoordinates() {
		double x;
		double y;
		x = DataInput.readDouble("~ Inserisci la coordinata x: > ");
		y = DataInput.readDouble("~ Inserisci la coordinata y: > ");
		return new Coordinates(x, y);
	}

	// restituisce il valore delle ascisse
	public double getX() {
		return x;
	}

	// permette di settare il valore delle ascisse
	public void setX(double x) {
		this.x = x;
	}

	// restituisce il valore delle coordinate
	public double getY() {
		return y;
	}

	// permette di settare il valore delle coordinate
	public void setY(double y) {
		this.y = y;
	}

	/*
	 * Calcolo delle distanze orbitali relative e assolute.
	 * Nel caso di pianeta-stella, la distanza relativa coincide con quella assoluta,
	 * poiché il sistema di riferimento ha al centro la stella in posizione Star(0.0,0.0)
	 */
	public static double planetAbsoluteDistance (Coordinates pointPlanet) {
		return Math.sqrt(Math.pow(pointPlanet.x, SQUARE) + Math.pow(pointPlanet.y, SQUARE));
	}

	/*
	 * Considero il pianeta come il centro del sottosistema. La luna considerata orbita
	 * con traiettoria avente il centro coincidente con il centro del pianeta a cui appartiene.
	 * Nell'HashMap moons ho la distanza pianeta-stella.
	 */
	public static double moonAbsoluteDistance (Coordinates pointMoon) {
		return Math.sqrt(Math.pow(pointMoon.x, SQUARE) + Math.pow(pointMoon.y, SQUARE));
	}

	// Trovo la distanza relativa tra due corpi celesti
	public static double relativeDistance (Coordinates point1, Coordinates point2) {
		return Math.sqrt(Math.pow(point1.x - point2.x, SQUARE) + Math.pow(point1.y - point2.y, SQUARE));
	}

	/*
	 * Metodo ceato per consentire la visualizzazione delle
	 * ascisse del centro di massa, memorizza la media ponderata delle ascisse.
	 */
	public void addX (double x) {
		this.x += x;
	}

	/*
	 * Metodo ceato per consentire la visualizzazione delle
	 * ordinate del centro di massa, memorizza la media ponderata delle ordinate.
	 */
	public void addY (double y) {
		this.y += y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Coordinates that = (Coordinates) o;
		return Double.compare(that.x, x) == 0 && Double.compare(that.y, y) == 0;
	}

	@Override
	public String toString() {
		return "[" +
				"x = " + x +
				", y = " + y +
				"]";
	}
}
