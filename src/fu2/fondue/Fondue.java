package fu2.fondue;

public class Fondue {
	
	private int _sigEnt, _sigSal, _ocupados, _numForquilles;
	
	public Fondue ( int tam ) {
		_numForquilles= tam;
		_ocupados = 0;
		_sigEnt = 1;
		_sigSal = 1;
	}

	public synchronized void cuinar(int  tems, Comensal c) {
		try {
			while ( _ocupados == _numForquilles ) wait();
			
			System.out.println(c.getNom() + " está cuinando ...");
			Thread.sleep(tems * 1000);
			System.out.println(c.getNom() + " a cacabat de cuinar.");
			
			_sigEnt = ( _sigEnt + 1 ) % _numForquilles;
			_ocupados++;
			notify();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}

	}

	public synchronized void mengar(int punt, Comensal c) {
		try {
			while( _ocupados == 0) wait();

			System.out.println(c.getNom() + " está menjant ...");
			Thread.sleep(punt * 1000);
			System.out.println(c.getNom() + " ha acabat de menjar.");
			
			_sigSal = ( _sigSal + 1 ) % _numForquilles;
			_ocupados--;
			notify();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
