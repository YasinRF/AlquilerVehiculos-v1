package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria;

import java.util.ArrayList;
import java.util.List;
import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;

public class Vehiculos implements IVehiculos {
	
	//ARRAY LIST
	
	private List <Vehiculo> coleccionVehiculos;
	
	public Vehiculos() {
		coleccionVehiculos = new ArrayList<>();
	}
	
	@Override
	public List<Vehiculo> get(){
		return new ArrayList<>(coleccionVehiculos);
	}
	
	@Override
	public int getCantidad() {
		return coleccionVehiculos.size();
	}
	
	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede insertar un vehículo nulo.");
		}

		if (coleccionVehiculos.contains(vehiculo)) {
			throw new OperationNotSupportedException("ERROR: Ya existe un vehículo con esa matrícula.");
		}

		coleccionVehiculos.add(vehiculo);
	}
	
	@Override
	public Vehiculo buscar(Vehiculo vehiculo) {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede buscar un vehículo nulo.");
		}
		int indice = coleccionVehiculos.indexOf(vehiculo);
		Vehiculo vehiculoBuscado = null; 
		if (coleccionVehiculos.contains(vehiculo)) {
			vehiculoBuscado = coleccionVehiculos.get(indice);
		} else {
			vehiculoBuscado = null;
		}
		return vehiculoBuscado;
	}
	
	@Override
	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {

		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede borrar un vehículo nulo.");
		}
		if (!coleccionVehiculos.contains(vehiculo)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún vehículo con esa matrícula.");
		}
		coleccionVehiculos.remove(vehiculo);
	}

}
