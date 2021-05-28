package it.unibs.fp.the_trinity.planetarium;

import it.unibs.fp.the_trinity.utilities.DataInput;
import java.util.HashMap;

public class Menu {
	// verra' utilizzato dal metodo "pause()" per "addormentare" il Thread
	private static final long MILLIS_PAUSE = 1500;
	// corrispondono agli stati che puo' assumere il programma
	private final int MAIN_STATE_CODE = 1;
	private final int CHOOSE_STATE_CODE = 2;
	private final int MANAGE_STATE_CODE = 3;
	private final int END_STATE_CODE = 0;

	private HashMap<String, StarSystem> starSystems;
	// lo stato definisce quale sezione del menu bisogna stampare
	private int state;
	// in systemKey viene salvata la key dello starSistem selezionato
	private String systemKey;


	// avvia il Planetarium, gestendo i vari menu che possono essere selezionati
	public void startPlanetarium() {
		boolean end = false;
		state = MAIN_STATE_CODE;
		starSystems = new HashMap<>();
		System.out.println("\n\n" + UsefulStrings.getWelcomePhrase());
		pause();

		do {
			switch (state) {
				case MAIN_STATE_CODE -> mainMenu();

				case CHOOSE_STATE_CODE -> chooseSystemMenu();

				case MANAGE_STATE_CODE -> manageSystemMenu();

				case END_STATE_CODE -> {
					/*
					 * stampa una delle frasi di "arrivederci"
					 * dopodiche' termina il programma
					 */
					end = true;
					System.out.println("\n" + UsefulStrings.getGoodbyePhrase());
					pause();
				}
			}
		} while (!end);
	}

	// gestisce il menu principale
	private void mainMenu() {
		String[] menu = UsefulStrings.MAIN_MENU_STRINGS;
		boolean doDefault = false;
		// conterra' la scelta dell'utente
		int userChoice;
		boolean error;

		System.out.println(UsefulStrings.FRAME);
		// controllo se esiste almeno un sistema solare, altrimenti non stampo l'opzione per accedere a uno di essi
		for (int i = 0; i < menu.length; i++) {
			if (i == UsefulStrings.CHOOSE_SYSTEM_OPTION && starSystems.isEmpty())
				continue;
			System.out.println(menu[i]);
		}

		userChoice = DataInput.readInt("» ");

		switch (userChoice) {
			case UsefulStrings.ADD_SYSTEM_OPTION -> addStellarSystem();
			case UsefulStrings.CHOOSE_SYSTEM_OPTION -> doDefault = chooseStellarSystem();
			case END_STATE_CODE -> state = END_STATE_CODE;
			default -> doDefault = true;
		}
		if (doDefault) {
			// se non viene selezionata nessuna delle opzioni precedenti stampo un messaggio di errore
			System.out.println("\n" + UsefulStrings.getErrorPhrase());
			pause();
		}
	}

	// gestisce il menu dove scegliere il sistema stellare
	private void chooseSystemMenu() {
		// conterra' il sistema stellare scelto dell'utente
		String userChoice;

		// stampo l'elenco di sistemi stellari
		System.out.println(UsefulStrings.FRAME);
		System.out.println(UsefulStrings.CHOOSE_MENU_STRING);
		for (StarSystem starSystem : starSystems.values())
			System.out.println("• " + starSystem.getName());

		// richiede il nome del sistema stellare da modificare (systemKey)
		userChoice = UsefulStrings.capitalize(DataInput.readNotEmptyString("» "));

		// controllo se è stata selezionata l'opzione di uscita
		if (userChoice.equals("0"))
			state = MAIN_STATE_CODE;
		else if (starSystems.containsKey(userChoice)) {
			systemKey = userChoice;
			state = MANAGE_STATE_CODE;
		} else {
			/*
			 * se la stringa inserita e' diversa da quella di uscita e
			 * dai nomi di sistemi stellari stampo un messaggio di errore
			 */
			System.out.println("\n" + UsefulStrings.getErrorPhrase());
			pause();
		}
	}

	// gestisce il menu dove possiamo modificare il sistema stellare
	private void manageSystemMenu() {
		StarSystem starSystem = starSystems.get(systemKey);
		String[] menu = UsefulStrings.SYSTEM_MENU_STRINGS;
		boolean doDefault = false;

		// stampo le varie opzioni solo se utilizzabili
		System.out.println(UsefulStrings.FRAME);
		for (int i = 0; i < menu.length; i++) {
			if ((i == UsefulStrings.ADD_STAR_OPTION && starSystem.hasStar()) ||
					(i == UsefulStrings.ADD_PLANET_OPTION && !starSystem.hasStar()) ||
					(i == UsefulStrings.REMOVE_PLANET_OPTION && !starSystem.hasPlanets()) ||
					(i == UsefulStrings.ADD_MOON_OPTION && !starSystem.hasPlanets()) ||
					(i == UsefulStrings.REMOVE_MOON_OPTION && !starSystem.hasMoons()) ||
					(i == UsefulStrings.FIND_CELESTIAL_BODY_OPTION && !starSystem.hasStar()) ||
					(i == UsefulStrings.SHOW_CELESTIAL_BODY_OPTION && !starSystem.hasStar()) ||
					(i == UsefulStrings.CALCULATE_MASS_CENTER_OPTION && !starSystem.hasStar()) ||
					(i == UsefulStrings.FIND_ROUTE_OPTION && !starSystem.hasPlanets()) ||
					(i == UsefulStrings.COLLISION_CHECK_OPTION && !starSystem.hasPlanets())
			)
				continue;
			System.out.println(menu[i]);
		}

		// richiede di inserire il numero dell'opzione del menu scelta
		int userChoice = DataInput.readInt("» ");

		switch (userChoice) {
			// OPZIONE 1
			case UsefulStrings.ADD_STAR_OPTION -> doDefault = addStar(starSystem);

			// OPZIONE 2
			case UsefulStrings.ADD_PLANET_OPTION -> doDefault = addPlanet(starSystem);

			// OPZIONE 3
			case UsefulStrings.ADD_MOON_OPTION -> doDefault = addMoon(starSystem);

			// OPZIONE 4
			case UsefulStrings.REMOVE_PLANET_OPTION -> doDefault = removePlanet(starSystem);

			// OPZIONE 5
			case UsefulStrings.REMOVE_MOON_OPTION -> doDefault = removeMoon(starSystem);

			// OPZIONE 6 - OPZIONE 7 (la prima parte di codice e' equivalente)
			case UsefulStrings.FIND_CELESTIAL_BODY_OPTION, UsefulStrings.SHOW_CELESTIAL_BODY_OPTION
					-> doDefault = findOrShowCelestialBody(userChoice, starSystem);

			// OPZIONE 8
			case UsefulStrings.CALCULATE_MASS_CENTER_OPTION -> doDefault = calculateMassCenter(starSystem);

			// OPZIONE 9
			case UsefulStrings.FIND_ROUTE_OPTION -> doDefault = findRoute(starSystem);

			// OPZIONE 10
			case UsefulStrings.COLLISION_CHECK_OPTION -> doDefault = collisionCheck(starSystem);

			// OPZIONE 0
			case END_STATE_CODE -> state = MAIN_STATE_CODE;

			default -> doDefault = true;
		}

		if (doDefault) {
			System.out.println("\n" + UsefulStrings.getErrorPhrase());
			pause();
		}
	}

	/* - MENU OPTIONS - */
	private void addStellarSystem() {
		// creo un nuovo sistema solare
		boolean error;
		String name;
		System.out.println();
		error = false;
		// controllo se il nome del sistema stellare inserito non e' gia' stato utilizzato
		do {
			if (error)
				System.out.println(UsefulStrings.CODE_NOT_AVAILABLE);
			name = (DataInput.readNotEmptyString(UsefulStrings.INSERT_SYSTEM));
			error = true;
		} while (isStarSystemNameAvailable(name));
		// aggiungo il sistema solare all'HashMap
		starSystems.put(UsefulStrings.capitalize(name), new StarSystem(name));
		systemKey = UsefulStrings.capitalize(name);
		// modifico lo state per entrare nel menu di gestione (manageSystemMenu())
		state = MANAGE_STATE_CODE;
	}

	private boolean chooseStellarSystem() {
		// controllo se l'opzione scelta è utilizzabile (se esiste almeno un sistema stellare)
		if (starSystems.isEmpty())
			return true;
		// modifico lo state per entrare nel menu di scelta (chooseSystemMenu())
		state = CHOOSE_STATE_CODE;
		return false;
	}

	private boolean addStar(StarSystem starSystem) {
		// controllo se l'opzione e' disponibile
		if (starSystem.hasStar())
			return true;
		// creo una nuova stella e la aggiungo al sistema stellare
		Star star = (Star)makeCelestialBody(CelestialBody.STAR_CODE, starSystem);
		starSystem.setStar(star);
		return false;
	}

	private boolean addPlanet(StarSystem starSystem) {
		if (!starSystem.hasStar())
			return true;
		if (starSystem.getStar().getPlanetsNumber() == Star.MAX_PLANETS) {
			// numero massimo di pianeti raggiunto
			System.out.println("\n" + UsefulStrings.MAX_PLANETS_REACHED);
			pause();
		} else {
			// creo un nuovo pianeta e lo aggiungo alla stella
			Planet planet = (Planet) makeCelestialBody(CelestialBody.PLANET_CODE, starSystem);
			starSystem.addPlanet(planet);
		}
		return false;
	}

	private boolean addMoon(StarSystem starSystem) {
		if (!starSystem.hasPlanets())
			return true;
		// aggiungo una luna al pianeta scelto
		Moon moon = (Moon)makeCelestialBody(CelestialBody.MOON_CODE, starSystem);
		if (!starSystem.addMoon(moon, moon.getPlanetCode())) {
			// numero massimo di lune attorno al pianeta raggiunto
			System.out.println("\n" + UsefulStrings.MAX_MOONS_REACHED);
			pause();
		}
		return false;
	}

	private boolean removePlanet(StarSystem starSystem) {
		if (!starSystem.hasPlanets())
			return true;
		/*
		 * richiedo il codice del pianeta da rimuovere, per poi rimuoverlo
		 * utilizzando il metodo "starSystem.removePlanet(planetCode)"
		 */
		String planetCode;
		System.out.println();
		boolean error = false;
		do {
			if (error)
				System.out.println(UsefulStrings.NO_CODE_MATCH);
			planetCode = DataInput.readNotEmptyString(UsefulStrings.INSERT_CODE_TO_REMOVE);
			error = true;
		} while (!starSystem.removePlanet(planetCode));
		System.out.println("\n" + UsefulStrings.CELESTIAL_BODY_REMOVED);
		pause();
		return false;
	}

	private boolean removeMoon(StarSystem starSystem) {
		if (!starSystem.hasMoons())
			return true;
		/*
		 * richiedo il codice della luna da rimuovere, per poi rimuoverla
		 * utilizzando il metodo "starSystem.removeMoon(moonCode)"
		 */
		String moonCode;
		System.out.println();
		boolean error = false;
		do {
			if (error)
				System.out.println(UsefulStrings.NO_CODE_MATCH);
			moonCode = DataInput.readNotEmptyString(UsefulStrings.INSERT_CODE_TO_REMOVE);
			error = true;
		} while (!starSystem.removeMoon(moonCode));
		System.out.println("\n" + UsefulStrings.CELESTIAL_BODY_REMOVED);
		pause();
		return false;
	}

	private boolean findOrShowCelestialBody(int userChoice, StarSystem starSystem) {
		CelestialBody cb;
		String code;
		if (!starSystem.hasStar())
			return true;
		// leggo il codice del corpo celeste da ricercare
		System.out.println();
		code = DataInput.readNotEmptyString(UsefulStrings.INSERT_CODE);
		/*
		 * utilizzo il metodo "getCelestialBody(code)" per cercare il corpo celeste, salvandolo in un oggetto
		 * definito CelestialBody cosi' da poter "accogliere" un oggetto Star, Planet o Moon.
		 */
		cb = starSystem.getCelestialBody(code);
		// controllo se l'oggetto e' null (in tal caso non esiste alcun corpo celeste con quel codice)
		if (cb == null)
			System.out.println("\n" + UsefulStrings.NO_CODE_MATCH);
		// distinguo in base a FIND_CELESTIAL_BODY_OPTION o SHOW_CELESTIAL_BODY_OPTION
		if (userChoice == UsefulStrings.FIND_CELESTIAL_BODY_OPTION) {
			// stampo una stringa diversa in base al tipo del corpo celeste relativo al codice
			switch (cb.MY_TYPE_CODE) {
				case CelestialBody.MOON_CODE -> // nel caso della luna stampo il pianeta al quale orbita attorno
						System.out.format(UsefulStrings.MOON_CODE_MATCH, code, ((Moon) cb).getPlanetCode());
				case CelestialBody.PLANET_CODE, CelestialBody.STAR_CODE -> System.out.println("\n" + UsefulStrings.CODE_MATCH);
			}
		} else {
			// stampo una stringa diversa in base al tipo del corpo celeste relativo al codice
			switch (cb.MY_TYPE_CODE) {
				case CelestialBody.MOON_CODE -> // nel caso della luna stampo il percorso necessario per raggiungerla
						System.out.format(UsefulStrings.MOON_CODE_FOUND, code, starSystem.getMoonPath(code));
				case CelestialBody.PLANET_CODE -> {
					// nel caso del pianeta devo stampare l'elenco delle lune che vi orbitano attorno
					System.out.format(UsefulStrings.PLANET_CODE_FOUND, code);
					for (Moon moon : starSystem.getPlanetMoons(code)) {
						System.out.print("\n• " + moon.getCode());
					}
				}
				case CelestialBody.STAR_CODE -> System.out.format(UsefulStrings.STAR_CODE_FOUND, code);
			}
		}
		pause();
		return false;
	}

	private boolean calculateMassCenter(StarSystem starSystem) {
		if (!starSystem.hasStar())
			return true;
		System.out.println("\n" + UsefulStrings.MASS_CENTER_MESSAGE + starSystem.getMassCenter().toString());
		return false;
	}

	private boolean findRoute(StarSystem starSystem) {
		if (!starSystem.hasPlanets()) {
			return true;
		}
		boolean error = false;
		String departureCode;
		String destinationCode;

		System.out.println();
		// controllo che i codici inseriti corrispondano a dei corpi celesti
		do {
			if (error)
				System.out.println(UsefulStrings.NO_CODE_MATCH);
			departureCode = UsefulStrings.capitalize(DataInput.readNotEmptyString(UsefulStrings.INSERT_CODE));
			error = true;
		} while (starSystem.isCelestialBodyCodeAvailable(departureCode));
		error = false;
		do {
			if (error)
				System.out.println(UsefulStrings.NO_CODE_MATCH);
			destinationCode = UsefulStrings.capitalize(DataInput.readNotEmptyString(UsefulStrings.INSERT_CODE));
			error = true;
		} while (starSystem.isCelestialBodyCodeAvailable(destinationCode));

		System.out.format(UsefulStrings.ROUTE_MESSAGE, destinationCode, departureCode);
		String route = starSystem.findCelestialBodiesRoute(departureCode, destinationCode);
		double distance = starSystem.calculateRouteDistance(route, UsefulStrings.SEPARATOR);

		System.out.println("\n" + route + "\nDistanza totale: " + distance);
		pause();
		return false;
	}

	private boolean collisionCheck(StarSystem starSystem) {
		if (!starSystem.hasPlanets())
			return true;
		System.out.println("\n" + (starSystem.areCollisionsPossible() ?
				UsefulStrings.COLLISION_POSSIBLE : UsefulStrings.COLLISION_NOT_POSSIBLE));
		pause();
		return false;
	}


	// controlla se il nome del sistema stellare e' ancora disponibile
	private boolean isStarSystemNameAvailable(String name) {
		return starSystems.containsKey(UsefulStrings.capitalize(name));
	}

	// crea un nuovo corpo celeste in base al TYPE, per poi aggiungerlo nel sistema stellare
	public CelestialBody makeCelestialBody(final int TYPE, StarSystem starSystem) {
		CelestialBody cb = null;
		String code;
		double mass;
		Coordinates coords = new Coordinates(0,0);

		System.out.println();
		boolean error = false;
		do {
			if (error)
				System.out.println(UsefulStrings.CODE_NOT_AVAILABLE);
			code = DataInput.readNotEmptyString(UsefulStrings.INSERT_CODE);
			error = true;
		} while (!starSystem.isCelestialBodyCodeAvailable(code));
		mass = DataInput.readPositiveDouble(UsefulStrings.INSERT_MASS);

		// nel caso della stella non chiedo le coordinate (sono 0,0 di default)
		if (TYPE != CelestialBody.STAR_CODE) {
			error = false;
			do {
				if (error)
					System.out.println(UsefulStrings.COORDS_NOT_AVAILABLE);
				coords = Coordinates.makeCoordinates();
				error = true;
			} while (!starSystem.areCoordinatesAvailable(coords));

		}
		// in base al tipo di corpo celeste da aggiungere
		switch (TYPE) {
			// creo un oggetto di tipo Star
			case CelestialBody.STAR_CODE -> cb = new Star(mass, code);

			// creo un oggetto di tipo Planet
			case CelestialBody.PLANET_CODE -> cb = new Planet(mass, coords, code, starSystem.getStar().getCode());

			// creo un oggetto di tipo Moon
			case CelestialBody.MOON_CODE -> {
				String planetCode;
				System.out.println();
				error = false;
				do {
					if (error)
						System.out.println(UsefulStrings.NO_CODE_MATCH);
					planetCode = DataInput.readNotEmptyString(UsefulStrings.INSERT_PLANET_CODE);
					error = true;
				} while (starSystem.getStar().getPlanet(planetCode) == null);
				// controllo se ho raggiunto il massimo numero di lune aggiungibili
				cb = new Moon(mass, coords, code, planetCode);
			}
		}
		return cb;
	}

	// genera una pausa nel sistema di 1500ms, "addormentando" il Thread del programma
	private void pause() {
		try {
			Thread.sleep(MILLIS_PAUSE);
		} catch (InterruptedException ignored) { }
	}
}

/*
 *
 *
 * MAIN MENU:
 * ___________________________________________________________________
 *
 * 	Benvenuto ...
 *  Scegli una delle seguenti opzioni per continuare:
 * 	[1] Aggiungi un nuovo sistema solare
 * 	[2] Accedi ad un sistema solare gia' esistente
 * 	[0] Esci
 *
 * 	SE DIGITO [1]
 * 		mi richiede informazioni sistema solare (nome etc), lo crea e
 * 		va alla sezione per modificare quel nuovo sistema solare
 * 		(SYSTEM MENU)
 *
 * 	SE DIGITO [2]
 * 		stampa la lista dei sistemi solari presenti. Esempio:
 * 		• Entra in "Sistema solare 1"
 * 		• Entra in "Sistema solare 2"
 * 			...
 *
 * 		Se digito "Sistema solare 1" va a SYSTEM MENU
 *
 * 	SE DIGITO [0]
 * 		Termino il programma
 * ___________________________________________________________________
 * ___________________________________________________________________
 *
 *
 * SYSTEM MENU:
 * ___________________________________________________________________
 *
 * (in base a quello che sara' stato aggiunti compariranno diverse
 * opzioni, ad esempio se manca ancora la Stella del sistema non si
 * potranno aggiungere pianeti etc...)
 * Opzioni complete:
 *
 * 	[1] Aggiungi la Stella del sistema solare
 * 			(sara' presente solo se non vi saranno ancora stelle)
 * 	[2] Aggiungi pianeta che orbita intorno a *nome stella*
 * 			(se non e' ancora presente la stella del sistema non commparira' questa opzione)
 * 	[3] Aggiungi luna che orbita intorno a un pianeta
 * 			(se non sono ancora presenti pianeti nel sistema non commparira' questa opzione)
 *  [4] Rimuovi pianeta
 * 			(se non sono ancora presenti pianeti nel sistema non commparira' questa opzione)
 *  [5] Rimuovi luna
 * 			(se non sono ancora presenti lune nel sistema non commparira' questa opzione)
 *  [6] Ricerca corpo celeste
 * 			(indica se il corpo celeste e' presente nel sistema stellare,
 * 			se si tratta di una luna stampa anche il pianeta intorno a cui orbita)
 *  [7] Visualizza corpo celeste
 * 			(se si tratta di un pianeta, stampa le lune che vi orbitano attorno,
 * 			se si tratta di una luna, stampa il percorso necessario per raggiungerla)
 * 	[8] Calcola il centro di massa
 *  [9] Calcola la rotta tra due corpi celesti
 *  [10] Controlla se si possono verificare collisioni
 * 	[0] Torna alla schermata principale
 *
 *
 */