package it.unibs.fp.the_trinity.planetarium;

/*
 * @author : THE TRINITY
 * [Baresi Marco, Contestabile Martina, Iannella Simone]
 *
 * Abbiamo utilizzato le HashMap<..,..> perche' la computazione risulta
 * essere particolarmente efficiente in molte delle casistiche presentatesi;
 * in particolare ogni qualvolta fosse necessario scandire l'intera lista
 * alla ricerca di uno specifico valore di un attributo presente nell'oggetto,
 * (in questo caso il Codice del corpo celeste) utilizzando il metodo proprio
 * delle HashMap "containKey(...)", sara' possibile effettuare questa operazione
 * con efficienza statisticamente ricorducibile a O(1)
 * (mentre nel metodo contains() degli ArrayList risulta essere pari a O(n))
 */

public class MainApp {

	public static void main(String[] args) {
		Menu menuInterface = new Menu();
		menuInterface.startPlanetarium();
	}
}
