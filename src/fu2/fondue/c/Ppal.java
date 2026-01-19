package fu2.fondue.c;

public class Ppal {

	public static void main(String[] args) {
		
		int numComensales = 10;
		int numFondueCarne = 5;
		int numFondueQueso = 3;
		
		Fondue[] fondues = new Fondue[2];
		fondues[0] = new Fondue(numFondueCarne, "Fondue Carne");
		fondues[1] =  new Fondue(numFondueQueso, "Fondue Queso");
		
		Comensal[] rnsComensales = new Comensal[numComensales];
		Thread[] thsComensales = new Thread[numComensales];
		
		// inicializaciones de comensales
		for ( int i = 0; i < numComensales; i++ ) {
			int punt = getRandomPunt();
			
			rnsComensales[i] = new Comensal(fondues, punt, "Comensal "+i);
			thsComensales[i] = new Thread(rnsComensales[i]);
		}
		
		for ( int i = 0; i < numComensales; i++ ) {
			thsComensales[i].start();
		}
	}

	public static int getRandomPunt() {
		return (int)(Math.random() * (7 - 4 + 1) + 4);
	}
}
