package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria;

import java.util.ArrayList;
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;

public class Vehiculos implements IVehiculos {
	
	//Array list
	
	private List <Vehiculo> coleccionVehiculos;
	
	public Vehiculos() {
		coleccionVehiculos = new ArrayList<>();
	}
	
	@Override
	public List<Vehiculo> get(){
		return new ArrayList<Vehiculo>(coleccionVehiculos);
	}
	
	@Override
	public int getCantidad() {
		return coleccionVehiculos.size();
	}
	
	@Override
	public void insertar(Vehiculo Vehiculo) throws OperationNotSupportedException {
		if (Vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede insertar un vehículo nulo.");
		}

		if (coleccionVehiculos.contains(Vehiculo)) {
			throw new OperationNotSupportedException("ERROR: Ya existe un vehículo con esa matrícula.");
		}

		coleccionVehiculos.add(Vehiculo);
	}
	
	@Override
	public Vehiculo buscar(Vehiculo Vehiculo) {
		if (Vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede buscar un vehículo nulo.");
		}
		if (coleccionVehiculos.contains(Vehiculo)) {
			return Vehiculo;
		}
		return null;
	}
	
	@Override
	public void borrar(Vehiculo Vehiculo) throws OperationNotSupportedException {

		if (Vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede borrar un vehículo nulo.");
		}
		if (!coleccionVehiculos.contains(Vehiculo)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún vehículo con esa matrícula.");
		}
		coleccionVehiculos.remove(Vehiculo);
	}

}
