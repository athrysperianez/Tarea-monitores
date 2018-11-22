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
package Ejercicio2;

public class Main {

	public static void main(String[] args) {
		Deposito dp = new Deposito();
		Productor pd = new Productor(dp);
		Consumidor cs = new Consumidor(dp);
		pd.start();
		cs.start();
		try {
			pd.join();
			cs.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(dp.getElementos());

	}

}

class Productor extends Thread {
	private Deposito deposito;

	public Productor(Deposito d) {
		deposito = d;
	}

	public void run() {
		for (int i = 1; i < 20; i++) {
			try {
				deposito.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			deposito.guardar();
		}
	}
}

class Consumidor extends Thread {
	private Deposito deposito;

	public Consumidor(Deposito d) {
		deposito = d;
	}

	public void run() {
		for (int i = 1; i < 20; i++) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			deposito.sacar();
		}
	}
}

class Deposito {
	private boolean elementos = false;
	
	public boolean getElementos() {
		return this.elementos;
	}

	public synchronized void guardar() {
		while (!elementos) {
			notifyAll();
			elementos = !elementos;
		}
	}

	public synchronized void sacar() {
		while (elementos)
			notifyAll();
			elementos = !elementos;
	}
}
