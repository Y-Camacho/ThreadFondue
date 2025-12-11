package fu2.fondue;

public class Ppal {

	public static void main(String[] args) {
		
		int numComensales = 10;
		int numFondueCarne = 5;
		
		Fondue fondueCarme = new Fondue(numFondueCarne);
		
		Comensal[] rnsComensales = new Comensal[numComensales];
		Thread[] thsComensales = new Thread[numComensales];
		
		// inicializaciones de comensales
		for ( int i = 0; i < numComensales; i++ ) {
			int punt = getRandomPunt();
			
			rnsComensales[i] = new Comensal(fondueCarme, punt, "Comensal "+i);
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
