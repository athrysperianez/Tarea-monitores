package Ejercicio3;

import java.util.concurrent.Semaphore;

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

public class Main {
	private static int SHEEP_NUMBER = 20;
	private static int SHEEP_HUNGER = 5;
	private static int SHEEP_DELAY = 5;
	private static int SHEEPARD_DELAY = 10;

	public static void main(String[] args) {
		Comedero cm = new Comedero();
		Pastor comandante = new Pastor(cm, SHEEPARD_DELAY);
		Pastor.ending = new Semaphore((SHEEP_NUMBER - 1)* -1);

		for (int i = 0; i < SHEEP_NUMBER; i++) {
			new Thread(new Oveja(cm, SHEEP_HUNGER, SHEEP_DELAY), Integer.toString(i+1)).start();
		}

		Thread th = new Thread(comandante);
		th.start();
		try {
			th.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Todas las ovejas estan dormidas");
	}

}
