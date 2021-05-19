package controller;

import java.util.Date;
import java.util.Scanner;

import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo */
	private Modelo modelo;

	/* Instancia de la Vista */
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * 
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller() {
		view = new View();
		modelo = new Modelo();
	}

	public void run() {
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String a = "";
		String b = "";
		String c = "";
		String d = "";
		String n = "";
		String res = "";

		while (!fin) {
			view.printMenu();

			int option = lector.nextInt();
			switch (option) {
			case 1:
				break;
			case 2:
				view.printMessage("--------- \nMusica para festejar: \nmin Energía?");
				a = lector.next();
				view.printMessage("--------- \nMax Energía?");
				b = lector.next();
				view.printMessage("--------- \nmin Danceability?");
				c = lector.next();
				view.printMessage("--------- \nmax Danceability?");
				d = lector.next();
				view.printMessage("--------- \n n?");
				n = lector.next();
				System.out.println(a + b + c + d);
				res = modelo.req2(a, b, c, d, Integer.parseInt(n));
				view.printMessage(res);
				break;

			case 3:
				view.printMessage("--------- \nMusica para estudiar: \nmin Instrumentalness?");
				a = lector.next();
				view.printMessage("--------- \nMax Instrumentalness?");
				b = lector.next();
				view.printMessage("--------- \nmin Tempo?");
				c = lector.next();
				view.printMessage("--------- \nmax Tempo?");
				d = lector.next();
				view.printMessage("--------- \n n?");
				n = lector.next();
				System.out.println(a + b + c + d);
				res = modelo.req3(a, b, c, d, Integer.parseInt(n));
				view.printMessage(res);
				break;

			case 4:
				view.printMessage("--------- \nreproducciones de los géneros musicales");
				res = modelo.req4();
				view.printMessage(res);
				break;
			case 5:
				view.printMessage("--------- \nreproducciones en horas \nmin hour (24h)?");
				a = lector.next();
				view.printMessage("--------- \nMax hour (24h)?");
				b = lector.next();
				res = modelo.req5(a, b);
				view.printMessage(res);
				break;
			case 6:
				view.printMessage("--------- \n Hasta pronto !! \n---------");
				lector.close();
				fin = true;
				break;

			default:
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}
}
