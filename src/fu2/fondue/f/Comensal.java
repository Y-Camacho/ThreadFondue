package fu2.fondue.f;

public class Comensal implements Runnable {
	
	private String nom;
	private int temsMenjant;
	private int puntPreferent;
	private int porcionsMenjades;
	private Fondue fondue;
	
	public Comensal(Fondue fon, int punt, String nom) {
		Thread.currentThread().setName(nom);
		this.nom = nom;
		this.temsMenjant = 2;
		this.porcionsMenjades = 0;
		this.puntPreferent = punt;
		this.fondue = fon;
	}
	
	private void Cuinar() {		
		try {
			
			fondue.agafarForquilla(this);
			
			System.out.println(this.getNom() + " está cuinando ...");
			Thread.sleep(puntPreferent * 1000);
			System.out.println(this.getNom() + " a cacabat de cuinar.");
			
			fondue.deixarForquilla(this);
			System.out.println(getNom() + " ha deixat la seva forquilla.");
			
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
			
			if(fondue.getPorciones() <= 0) {
				System.out.println("*  -- No hay más porciones --  *");
				break;
			}
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
}
