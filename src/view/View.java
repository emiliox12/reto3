package view;

import model.logic.Modelo;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("1. ");
			System.out.println("2. Musica para festejar");
			System.out.println("3. Musica para estudiar");
			System.out.println("4. reproducciones de los géneros musicales");
			System.out.println("5. reproducciones en horas (24h)");
			System.out.println("6. cerrar");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printMessage(String mensaje) {
			System.out.println(mensaje);
		}		
		
		public void printModelo(Modelo modelo)
		{
			System.out.println(modelo);
		}
}
