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

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class Main {

	public static String textData = "";
	public static volatile AtomicReference<Integer> count = new AtomicReference<Integer>(0);
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduzca texto a buscar");
		textData = sc.nextLine();
		Thread thA = null;
		Thread thE = null;
		Thread thI = null;
		Thread thO = null;
		Thread thU = null;
		try {
			thA = new Thread(new Contador('a'));
			thE = new Thread(new Contador('E'));
			thI = new Thread(new Contador('i'));
			thO = new Thread(new Contador('O'));
			thU = new Thread(new Contador('u'));

		} catch (NotVowelException e) {
			e.printStackTrace();
		}
		
		thA.start();
		thE.start();
		thI.start();
		thO.start();
		thU.start();
		
		try {
			thA.join();
			thE.join();
			thI.join();
			thO.join();
			thU.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(count.get());

	}

}
