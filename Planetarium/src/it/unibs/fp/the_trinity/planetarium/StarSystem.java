package it.unibs.fp.the_trinity.planetarium;

import it.unibs.fp.the_trinity.utilities.Range;

import java.util.ArrayList;

public class StarSystem {
	private String name;
	private Star star;
	private Coordinates massCenter;


	// permette di istanziare un oggetto di tipo StarSystem
	public StarSystem(String name) {
		this.name = UsefulStrings.capitalize(name);
		massCenter = new Coordinates(0, 0);
	}


	// restituisce il nome del sistema stellare
	public String getName() {
		return name;
	}


	/* - STARS METHODS - */
	// permette di modificare la stella
	public void setStar(Star star) {
		this.star = star;
	}

	// restituisce l'oggetto star
	public Star getStar() {
		return star;
	}

	/*
	 * Permette di verificare se il sistema possiede già una stella.
	 * In caso di risposta affermativa, non sarà possibile inserire
	 * ulteriori stelle, perché i Sistemi Stellari di Maghatea permettono
	 * a ciascun sistema di possedere al massimo una stella.
	 * (tale gestione e' presente nel Menu)
	 */
	public boolean hasStar() {
		return (star != null);
	}


	/* - PLANETS METHODS - */
	/*
	 * Permette l'aggiunta di nuovi pianeti, se questi:
	 * 1) Non hanno raggiunto il numero massimo consentito.
	 * 2) Rispettano i requisiti minimi.
	 * (tale gestione e' presente nel Menu)
	 */
	public boolean addPlanet(Planet planet) {
		return star.addPlanet(planet);
	}

	// invoca il metodo omonimo nell'oggetto star
	public boolean removePlanet(String planetCode) {
		return star.removePlanet(planetCode) != null;
	}

	// verifica la presenza di pianeti nel sistema stellare
	public boolean hasPlanets() {
		return hasStar() && star.hasPlanets();
	}
	/*
	 * restituisce un ArrayList contenente le lune orbitanti attorno
	 * al pianeta associato al planetCode
	 */
	public ArrayList<Moon> getPlanetMoons(String planetCode) {
		return star.getPlanet(planetCode).getMoonsArrayList();
	}


	/* - MOONS METHODS - */
	/*
	 * Permette l'aggiunta di nuove lune, se queste:
	 * 1) Non hanno raggiunto il numero massimo consentito.
	 * 2) Rispettano i requisiti minimi.
	 */
	public boolean addMoon(Moon moon, String planetCode) {
		return star.getPlanet(UsefulStrings.capitalize(planetCode)).addMoon(moon);
	}

	// invoca il metodo omonimo nel pianeta contenente la luna
	public boolean removeMoon(String moonCode) {
		moonCode = UsefulStrings.capitalize(moonCode);
		ArrayList<Planet> planets = star.getPlanetsArrayList();
		for (Planet planet : planets) {
			if (planet.containsMoon(moonCode)) {
				planet.removeMoon(moonCode);
				return true;
			}
		}
		return false;
	}

	// verifica la presenza di lune nel sistema stellare
	public boolean hasMoons() {
		boolean hasMoons = false;
		if (hasPlanets()) {
			for (Planet planet : star.getPlanetsArrayList())
				if (planet.hasMoon()) {
					hasMoons = true;
					break;
				}
		}
		return hasMoons;
	}

	/*
	 * Visualizzazione del percorso da effettare per raggiungere
	 * una luna, scelta dall'utente, del sistema stellare.
	 */
	public String getMoonPath(String moonCode) {
		return "[ " + star.getMoonPath(UsefulStrings.capitalize(moonCode)) + " ]";
	}


	/* - MASS CENTER METHODS - */
	// restituisce il valore del centro di massa calcolato in updateMassCenter()
	public Coordinates getMassCenter() {
		updateMassCenter();
		return massCenter;
	}

	// calcolo il centro di massa
	private void updateMassCenter() {
		double massStar = star.getMass();
		double massPlanets = 0.0;
		double massMoons = 0.0;
		double massTotal;
		Coordinates wsp = new Coordinates (0.0,0.0); //weighted sum of positions,
		//somma pesata delle masse.

		ArrayList<Planet> planets = star.getPlanetsArrayList();
		ArrayList<Moon> moons;

		for (Planet p : planets) {
			moons = p.getMoonsArrayList();
			massPlanets += p.getMass();
			wsp.addX(p.getMass() * p.getCoords().getX());
			wsp.addY(p.getMass() * p.getCoords().getY());
			for (Moon m : moons) {
				massMoons += m.getMass();
				wsp.addX(m.getMass() * m.getCoords().getX());
				wsp.addY(m.getMass() * m.getCoords().getY());
			}
		}

		massTotal = massStar + massPlanets + massMoons;

		massCenter.setX(wsp.getX()/massTotal);
		massCenter.setY(wsp.getY()/massTotal);
	}


	/* - CELESTIAL BODIES METHODS - */
	// restituisce una stella, un pianeta o una luna in base al codice passato
	public CelestialBody getCelestialBody(String code) {
		code = UsefulStrings.capitalize(code);
		CelestialBody cb = null;
		if (star.getCode().equals(code))
			cb = star;
		else if(star.getPlanets().containsKey(code)) {
			cb = star.getPlanet(code);
		}
		else {
			for (Planet planet : star.getPlanets().values()) {
				if (planet.getMoons().containsKey(code)) {
					cb = planet.getMoon(code);
					break;
				}
			}
		}
		return cb;
	}

	/*
	 * Visualizzazione del percorso da effettare per raggiungere
	 * un corpo celeste, scelto dall'utente, del sistema stellare a partire
	 * da un corpo celeste scelto sempre dall'utente. Il tutto va effettuato
	 * trovando il percorso effettuato per gerarchia (Stella, Pianeta, Luna),
	 * e non tramite viaggio diretto, e la distanza totale percorsa.
	 * (lo abbiamo sviluppato per via ricorsiva)
	 */
	public String findCelestialBodiesRoute(String departureCode, String destinationCode) {
		if (departureCode.equals(destinationCode))
			return departureCode;

		String route = "";

		CelestialBody cb = getCelestialBody(departureCode);
		if (cb == null)
			return UsefulStrings.NO_CODE_MATCH;

		switch (cb.MY_TYPE_CODE) {
			case CelestialBody.STAR_CODE -> {
				Star star = (Star)cb;
				if (star.containsPlanet(destinationCode))
					route += departureCode + UsefulStrings.SEPARATOR + destinationCode;
				else if (star.containsMoon(destinationCode))
					route += star.getMoonPath(destinationCode);
				else return UsefulStrings.NO_CODE_MATCH;
			}
			case CelestialBody.PLANET_CODE -> {
				Planet planet = (Planet)cb;
				route += departureCode + UsefulStrings.SEPARATOR;
				if (planet.getStarCode().equals(destinationCode) || planet.containsMoon(destinationCode))
					route += destinationCode;
				else
					route += findCelestialBodiesRoute(planet.getStarCode(), destinationCode);
			}
			case CelestialBody.MOON_CODE -> {
				route += departureCode + UsefulStrings.SEPARATOR;
				Moon moon = (Moon)cb;
				if (moon.getPlanetCode().equals(destinationCode))
					route += destinationCode;
				else
					route += findCelestialBodiesRoute(moon.getPlanetCode(), destinationCode);
			}
		}

		return route.contains(UsefulStrings.NO_CODE_MATCH) ? UsefulStrings.NO_CODE_MATCH : route;
	}

	/*
	 * calcola la distanza totale fra i vari corpi celesti i cui codici sono presenti nella stringa "route"
	 * e separati dal SEPARATOR
	 */
	public double calculateRouteDistance(String route, final String SEPARATOR) {
		double distance = 0;
		String[] codes = route.split(SEPARATOR);
		for (int i=0; i<codes.length-1; i++) {
			distance += Coordinates.relativeDistance(getCelestialBody(codes[i]).getCoords(), getCelestialBody(codes[i+1]).getCoords());
		}
		return distance;
	}

	// controlla se il nome del corpo celeste e' ancora disponibile
	public boolean isCelestialBodyCodeAvailable(String code) {
		return star == null || getCelestialBody(UsefulStrings.capitalize(code)) == null;
	}

	// controlla se alle coordinate inserite e' gia' presente un corpo celeste
	public boolean areCoordinatesAvailable(Coordinates coords) {
		boolean areAvailable = true;
		if (star.getCoords().equals(coords))
			areAvailable = false;
		else {
			for (Planet planet : star.getPlanetsArrayList()) {
				if (planet.getCoords().equals(coords)) {
					areAvailable = false;
				} else {
					for (Moon moon : planet.getMoonsArrayList())
						if (moon.getCoords().equals(coords)) {
							areAvailable = false;
							break;
						}
				}
				if (!areAvailable)
					break;
			}
		}
		return areAvailable;
	}

	// controlla se si possono verificare collisioni tra corpi celesti
	public boolean areCollisionsPossible() {
		ArrayList<Range> radiusRanges = new ArrayList<>();
		ArrayList<Double> distances = new ArrayList<>();
		Range tempRange;
		double radius;
		boolean conflict = false;
		double maxRadius;
		double planetRadius;
		int comparision;

		// aggiungo il "range" occupato del sole
		Range starRange = new Range(0, 0);
		radiusRanges.add(starRange);

		/*
		 * effettuo una scansione dei pianeti e delle lune, salvando in "distances" le distanze (radius) dal pianeta
		 * ad ogni luna che vi orbita attorno. Se la distanza e' gia' presente, trovo un conflitto tra due lune dello
		 * stesso pianeta; altrimenti salvo in maxRadius la distanza massima (che corrisponde al raggio della luna piu'
		 * lontana) per poi calcolare il range minimo e massimo occupato dal pianeta e dalle sue lune.
		 * infine controllo se il range entra in conflitto con altri range gia' inseriti, ed in tal caso si verifichera'
		 * collisione.
		 * nel caso di un possibile conflitto con la Stella, devo tener conto che quest'ultimo non ha alcuna orbita e,
		 * quindi, se il controllo risulta essere EQUALS.
		 */
		endloop:
		for (Planet planet : star.getPlanetsArrayList()) {
			distances.clear();

			planetRadius = Coordinates.planetAbsoluteDistance(planet.getCoords());
			// aggiungo la distanza relativa del pianeta da se stesso
			distances.add(0.);
			for (Moon moon : planet.getMoonsArrayList()) {
				radius = Coordinates.relativeDistance(moon.getCoords(), planet.getCoords());
				if (distances.contains(radius) || planetRadius == radius) {
					conflict = true;
					break endloop;
				}
				distances.add(radius);
			}
			maxRadius = distances.stream().max(Double::compare).get();

			tempRange = new Range(planetRadius-maxRadius, planetRadius+maxRadius);
			for (Range range : radiusRanges) {
				comparision = range.compare(tempRange);
				if (comparision == Range.EQUALS || (comparision == Range.CONFLICT && !range.equals(starRange))) {
					conflict = true;
					break endloop;
				}
			}
			radiusRanges.add(tempRange);
		}
		return conflict;
	}
}