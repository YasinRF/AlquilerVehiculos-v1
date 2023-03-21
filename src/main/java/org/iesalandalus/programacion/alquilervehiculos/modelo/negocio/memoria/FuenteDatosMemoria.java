package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria;

import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;

public class FuenteDatosMemoria implements IFuenteDatos {
	
	
	@Override
	public IClientes crearClientes() {
		IClientes cliente = new Clientes();
		return cliente;
	}
	
	@Override
	public IVehiculos crearVehiculos() {
		IVehiculos vehiculo = new Vehiculos();
		return vehiculo;
	}
	
	@Override
	public IAlquileres crearAlquileres() {
		IAlquileres alquiler = new Alquileres();
		return alquiler;
	}
	

}
