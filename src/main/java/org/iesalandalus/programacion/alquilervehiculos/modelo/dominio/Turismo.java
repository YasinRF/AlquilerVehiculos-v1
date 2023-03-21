package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

public class Turismo extends Vehiculo {
	
	private final static int FACTOR_CILINDRADA = 10;
	
	private int cilindrada;
	
	
	// CREAMOS LOS CONSTRUCTORES Y EL CONSTRUCTOR COPIA

	public Turismo(String marca, String modelo, int cilindrada, String matricula) {
		super(marca, modelo, matricula);
		setCilindrada(cilindrada);
	}

	public Turismo(Turismo turismo) {
		super(turismo);
		if (turismo == null) {
			throw new NullPointerException("ERROR: No es posible copiar un turismo nulo.");
		}
		cilindrada = turismo.getCilindrada();
	}

	// GETTERS Y SETTERS

	public int getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(int cilindrada) {
		if (cilindrada <= 0 || cilindrada > 5000) {
			throw new IllegalArgumentException("ERROR: La cilindrada no es correcta.");
		}
		this.cilindrada = cilindrada;
	}
	
	@Override
	public int getFactorPrecio() {
		int precioTurismo = cilindrada/FACTOR_CILINDRADA;
		return precioTurismo;
	}

	// CREAMOS EL TOSTRING

	@Override
	public String toString() {
		return String.format("%s %s (%d cc) - %s",getMarca(),getModelo(), cilindrada, getMatricula());
	}

}
