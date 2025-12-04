package fu2.fondue;

public class Comensal implements Runnable {
	
	private String nom;
	private int temsMenjant;
	private int puntPreferent;
	private Fondue fondue;
	
	public Comensal(int punt, String nom) {
		Thread.currentThread().setName(nom);
		this.nom = nom;
		this.temsMenjant = 2;
		this.puntPreferent = punt;
	}
	
	private void Cuinar() {
		fondue.cuinar(temsMenjant, this);
	}

	private void Menjar() {
		fondue.mengar(puntPreferent, this);
	}

	@Override
	public void run() {
		
		Cuinar();
		Menjar();
		
		System.out.println(getNom() + " ha deixat la seva forquilla.");
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
