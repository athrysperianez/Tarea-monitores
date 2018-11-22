package Ejercicio2;

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
		if (dp.getElementos())
			System.out.println("Ejecucion terminada con produccion sobrante\n");
		else
			System.out.println("Ejecucion terminada sin produccion sobrante\n");

		System.out.println("Producido:" + pd.getProduccion());
		System.out.println("\nConsumido:" + cs.getConsumicion());
	}

}

class Productor extends Thread {
	private Deposito deposito;
	private int produccion = 0;//Cuenta de la produccion

	public Productor(Deposito d) {
		deposito = d;
	}

	public void run() {
		for (int i = 1; i < 20; i++) {
			synchronized (deposito) {//Bloque sincronizado usando como monitor el objeto deposito
				while (deposito.getElementos()) {//Mientras haya un elemento no se necesita prodccion
					try {
						System.out.println("El productor esta a la espara\n");
						deposito.wait();//Se duerme y libera su hueco en el monitor de deposio
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				deposito.guardar();//Se produce un objeto y se guarda
				System.out.println("Se ha producido una unidad\n"); 
				this.produccion++;
				deposito.notify();//Se avisa al monitor de deposito
			}
		}
	}

	public int getProduccion() {
		return produccion;
	}

	public void setProduccion(int produccion) {
		this.produccion = produccion;
	}
}

class Consumidor extends Thread {
	private Deposito deposito;
	private int consumicion = 0;//Cuenta de la consumiciones

	public Consumidor(Deposito d) {
		deposito = d;
	}

	public void run() {
		for (int i = 1; i < 20; i++) {
			synchronized (deposito) {
				while (!deposito.getElementos()) {
					try {
						System.out.println("El consumidor esta a la espera\n");
						deposito.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				deposito.sacar();
				System.out.println("Se ha consumido una unidad\n");
				this.consumicion++;
				deposito.notify();
			}
		}
	}

	public int getConsumicion() {
		return consumicion;
	}

	public void setConsumicion(int consumicion) {
		this.consumicion = consumicion;
	}
}

class Deposito {
	private boolean elementos = false;

	public boolean getElementos() {
		return this.elementos;
	}

	public void guardar() {
		elementos = true;
	}

	public void sacar() {
		elementos = false;
	}
}
