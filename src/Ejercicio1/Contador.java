/*
 *Creado por Elias Periañez
 *20 nov. 2018
 *Como parte del proyecto Tarea 4-Monitores (Ultra Instinto)
 *Este archivo esta bajo la licencia de Creative Commons Reconocimiento 4.0 Internacional (Más informacion https://creativecommons.org/licenses/by/4.0/)
________________________________________________________________________________________________________________________________________________________
 *Created by Elias Periañez
 *20 nov. 2018
 *As part of the project Tarea 4-Monitores (Ultra Instinto)
 *This file is under the Creative Commons Attribution 4.0 International (More info here https://creativecommons.org/licenses/by/4.0/)
 */
package Ejercicio1;

public class Contador implements Runnable {

	private char vocal;

	public Contador(char lookingFor) throws NotVowelException {
		if (isVowel(lookingFor)) {
			this.vocal = Character.toUpperCase(lookingFor);
		} else {
			throw new NotVowelException();
		}
	}

	public void run() {
		String txt = Main.textData;
		for(int i = 0; i < txt.length();i++) {
			if(Character.toUpperCase(txt.charAt(i)) == this.vocal) {
				Main.count.set(Main.count.get()+1);
			}
		}
	}

	private static boolean isVowel(char c) {
		return "AEIOUaeiou".indexOf(c) != -1;
	}
}

class NotVowelException extends Exception {
	public NotVowelException() {
		super("Character is not a vowel");
	}
}