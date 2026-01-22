package fu2.fondue.d;

public class Fondue {
	
	public String _name;
	private int _ocupados, _numForquilles;
	
	public Fondue ( int tam ) {
		_numForquilles= tam;
		_ocupados = 0;
	}
	
	public Fondue ( int tam, String name) {
		_name = name;
		_numForquilles= tam;
		_ocupados = 0;
	}

	public synchronized void agafarForquilla(Comensal c) throws InterruptedException {
		
		while ( _ocupados == _numForquilles ) {
			System.out.println(c.getNom() + " está esperant ...(" + this.getName() + ")");
			wait();
		}
		
		_ocupados++;
		notify();
	}

	public synchronized void deixarForquilla(Comensal c) throws InterruptedException {
		_ocupados--;
		notify();
	}
	
	public String getName() { return this._name; }
	public void setName( String name ) { this._name = name; }

}
