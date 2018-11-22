package Ejercicio3;

import java.util.concurrent.TimeUnit;

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

public class Oveja implements Runnable {

	private Comedero comedero;
	private int alimentacion;
	private int delay;

	public Oveja(Comedero cm, int alimentacion, int delay) {
		this.setComedero(cm);
		this.setAlimentacion(alimentacion);
		this.setDelay(delay);
	}

	@Override
	public void run() {
		int i = 0;
		while (i < alimentacion) { // He usado while en vez de for ya que esta feo un interrupted exception en un for
			try {
				TimeUnit.MILLISECONDS.sleep(delay);
				this.comer();
				System.out.println(
						"La oveja " + Thread.currentThread().getName() + " ha comido un total de " + (i + 1) + " veces.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
		}
		System.out.println("La oveja " + Thread.currentThread().getName() + " se duerme.");
		Pastor.ending.release();
	}

	private void comer() throws InterruptedException {
		synchronized (comedero) {
			while (!comedero.haveFood()) {
				comedero.wait();
			}
			this.comedero.comer();
			comedero.notify();
		}
	}

	public Comedero getComedero() {
		return comedero;
	}

	public void setComedero(Comedero comedero) {
		this.comedero = comedero;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public int getDelay() {
		return this.delay;
	}

	public int getAlimentacion() {
		return alimentacion;
	}

	public void setAlimentacion(int alimentacion) {
		this.alimentacion = alimentacion;
	}

}
