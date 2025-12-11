package fu2.fondue;

public class Fondue {
	
	private int _ocupados, _numForquilles;
	
	public Fondue ( int tam ) {
		_numForquilles= tam;
		_ocupados = 0;
	}

	public synchronized void agafarForquilla(Comensal c) throws InterruptedException {
		
		while ( _ocupados == _numForquilles ) {
			System.out.println(c.getNom() + " está esperant ...");
			wait();
		}
		
		_ocupados++;
		notify();
	}

	public synchronized void deixarForquilla(Comensal c) throws InterruptedException {
		_ocupados--;
		notify();
	}

}
