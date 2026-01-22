package fu2.fondue.f;

public class Fondue {
	
	private int _ocupados, _numForquilles, _porciones;
	
	public Fondue ( int tam, int porciones ) {
		_numForquilles= tam;
		_ocupados = 0;
		_porciones = porciones;
	}

	public synchronized void agafarForquilla(Comensal c) throws InterruptedException {
		
		while ( _ocupados == _numForquilles ) {
			System.out.println(c.getNom() + " está esperant ...");
			wait();
		}
		
		_ocupados++;
		_porciones--;
		notify();
	}

	public synchronized void deixarForquilla(Comensal c) throws InterruptedException {
		_ocupados--;
		notify();
	}

	public int getPorciones() { return this._porciones; }
}
