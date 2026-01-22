package fu2.fondue.d;

import java.util.Random;

public class Comensal implements Runnable {
	
	private Random random = new Random();
	
	private String nom;
	private int temsMenjant;
	private int puntPreferent;
	private int porcionsMenjades;
	private Fondue fondue;
	private Fondue[] fonduesDisponibles;
	private boolean tienePreferencias;
	private boolean prefDisponible;
	
	public Comensal(Fondue[] fondues, int punt, String nom, String fonduePref) {
		Thread.currentThread().setName(nom);
		this.nom = nom;
		this.temsMenjant = 2;
		this.porcionsMenjades = 0;
		this.puntPreferent = punt;
		this.fondue = this.choosePrefFondue(fondues, fonduePref);
		this.fonduesDisponibles = fondues;
		this.tienePreferencias = ( fonduePref != null && !fonduePref.equals("")); 
	}
	
	private void Cuinar() {		
		try {
			
			fondue.agafarForquilla(this);
			
			System.out.println(this.getNom() + " está cuinando ... (" + this.fondue.getName() + ")");
			Thread.sleep(puntPreferent * 1000);
			System.out.println(this.getNom() + " a cacabat de cuinar.(" + this.fondue.getName() + ")");
			
			fondue.deixarForquilla(this);
			System.out.println(getNom() + " ha deixat la seva forquilla.(" + this.fondue.getName() + ")");
			
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
			
			if ( fondue == null ) this.fondue = chooseRandomFondue(fonduesDisponibles);
			Cuinar();
			// Si la fondue de preferencia no está disponible, la fondue se pone a null para que 
			// en el siguiente ciclo se elija otra al azar;
			if ( !prefDisponible ) this.fondue = null;
			Menjar();
		}
	}
	
	private Fondue chooseRandomFondue(Fondue[] fns) {
		return fns[random.nextInt(2)];
	}
	
	private Fondue choosePrefFondue(Fondue[] fns, String pref) {
		
		
		for (Fondue fon : fns) {
		
			if( !tienePreferencias ) continue;
			
			if ( fon.getName().contains(pref)) {
				return fon;
			}
		}
		
		this.prefDisponible = false;
		return chooseRandomFondue(fns); // 
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

	public Fondue[] getFonduesDisponibles() {
		return fonduesDisponibles;
	}

	public void setFonduesDisponibles(Fondue[] fonduesDisponibles) {
		this.fonduesDisponibles = fonduesDisponibles;
	}

	public boolean isTienePreferencias() {
		return tienePreferencias;
	}

	public void setTienePreferencias(boolean tienePreferencias) {
		this.tienePreferencias = tienePreferencias;
	}
	
	
}
