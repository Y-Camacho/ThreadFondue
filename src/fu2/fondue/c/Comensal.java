package fu2.fondue.c;

import java.util.Random;

public class Comensal implements Runnable {
	
	private Random random = new Random();
	
	private String nom;
	private int temsMenjant;
	private int puntPreferent;
	private int porcionsMenjades;
	private Fondue fondue;
	private Fondue fondue2;
	private String fondueEnUso;
	
	public Comensal(Fondue[] fondues, int punt, String nom) {
		
		int i = random.nextInt(2);
		
		//Thread.currentThread().setName(nom);
		this.nom = nom;
		this.temsMenjant = 2;
		this.porcionsMenjades = 0;
		this.puntPreferent = punt;
		this.fondue = fondues[i];
		this.fondue2 = fondues[1-i];
	}
	
	private void Cuinar() {		
		try {
			
			if( fondue.tryAgafarForquilla(this)) {
				System.out.println(this.getNom() + " intenta cocinar en ...(" + this.fondue.getName() + ")");
				this.fondueEnUso = this.fondue.getName();
			} else if(fondue2.tryAgafarForquilla(this)){
				System.out.println(this.getNom() + " cambia de fondue  ...(" + this.fondue2.getName() + ")");
				this.fondueEnUso = this.fondue2.getName();
			} else {
				System.out.println("Ambas fondues ocupadas, "+ this.getNom() + " esperará");
				fondue2.agafarForquilla(this);
				fondueEnUso = fondue2.getName();
			}
			
			System.out.println(this.getNom() + " está cuinando ... (" + this.fondueEnUso + ")");
			Thread.sleep(puntPreferent * 1000);
			System.out.println(this.getNom() + " a cacabat de cuinar.(" + this.fondueEnUso + ")");
			
			if(fondueEnUso.equals(fondue.getName())) {
				fondue.deixarForquilla(this);
			} else {
				fondue2.deixarForquilla(this);
			}
			System.out.println(getNom() + " ha deixat la seva forquilla.(" + this.fondueEnUso + ")");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void Menjar() {
		try {
			
			System.out.println(this.getNom() + " está menjant ...");
			Thread.sleep(temsMenjant * 1000);
			this.porcionsMenjades++;
			System.out.println(this.getNom() + " ha acabat de menjar - " + porcionsMenjades);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		while ( true ) {
			Cuinar();
			Menjar();
		}
	}

	// ---- 
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getTemsMenjant() {
		return temsMenjant;
	}
	public void setTemsMenjant(int temsMenjant) {
		this.temsMenjant = temsMenjant;
	}

	public int getPuntPreferent() {
		return puntPreferent;
	}
	public void setPuntPreferent(int puntPreferent) {
		this.puntPreferent = puntPreferent;
	}

	public Fondue getFondue() {
		return fondue;
	}
	public void setFondue(Fondue fondue) {
		this.fondue = fondue;
	}

	public Fondue getFondue2() {
		return fondue2;
	}
	public void setFondue2(Fondue fondue2) {
		this.fondue2 = fondue2;
	}
	
}
