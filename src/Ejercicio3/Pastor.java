package Ejercicio3;

import java.util.concurrent.Semaphore;
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

public class Pastor implements Runnable {

	private Comedero comedero;
	public static Semaphore ending;
	private int time;

	public Pastor(Comedero cm, int time) {
		this.setComedero(cm);
		this.setTime(time);
	}

	@Override
	public void run() {
		while (!ending.tryAcquire()) {
			try {
				TimeUnit.MILLISECONDS.sleep(time);
				this.rellenar();
				System.out.println("Se ha rellenado el comedero");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void rellenar() throws InterruptedException {
		synchronized (comedero) {
			while (comedero.haveFood()) {
				comedero.wait();
			}
			this.comedero.setFood(true);
			comedero.notify();
		}
	}

	public Comedero getComedero() {
		return comedero;
	}

	public void setComedero(Comedero comedero) {
		this.comedero = comedero;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

}
