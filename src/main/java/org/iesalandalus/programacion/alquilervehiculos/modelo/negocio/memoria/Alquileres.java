package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;

public class Alquileres implements IAlquileres {

	// ARRAY LIST

	private List<Alquiler> coleccionAlquileres;

	public Alquileres() {
		coleccionAlquileres = new ArrayList<>();
	}

	@Override
	public List<Alquiler> get() {
		return new ArrayList<>(coleccionAlquileres);
	}

	@Override
	public List<Alquiler> get(Cliente cliente) {
		List<Alquiler> lista = new ArrayList<>();
		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getCliente().equals(cliente)) {
				lista.add(alquiler);
			}
		}
		return lista;
	}

	@Override
	public List<Alquiler> get(Vehiculo vehiculo) {
		List<Alquiler> lista = new ArrayList<>();
		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getVehiculo().equals(vehiculo)) {
				lista.add(alquiler);
			}
		}
		return lista;
	}

	@Override
	public int getCantidad() {
		return coleccionAlquileres.size();
	}

	@Override
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}
		comprobarAlquiler(alquiler.getCliente(), alquiler.getVehiculo(), alquiler.getFechaAlquiler());
		coleccionAlquileres.add(alquiler);
	}

	private void comprobarAlquiler(Cliente cliente, Vehiculo vehiculo, LocalDate fechaAlquiler)
			throws OperationNotSupportedException {
		for (Alquiler alquiler : get()) {
			if (alquiler.getCliente().equals(cliente)) {
				if (alquiler.getFechaDevolucion() == null) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
				}
				if (alquiler.getFechaDevolucion().isAfter(fechaAlquiler)
						|| alquiler.getFechaDevolucion().isEqual(fechaAlquiler)) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");
				}
			}

			if (alquiler.getVehiculo().equals(vehiculo)) {
				if (alquiler.getFechaDevolucion() == null) {
					throw new OperationNotSupportedException("ERROR: El turismo está actualmente alquilado.");
				}
				if (alquiler.getFechaDevolucion().isAfter(fechaAlquiler)
						|| alquiler.getFechaDevolucion().isEqual(fechaAlquiler)) {
					throw new OperationNotSupportedException("ERROR: El turismo tiene un alquiler posterior.");
				}
			}
		}
		

	}

	private Alquiler getAlquilerAbierto(Cliente cliente) {
		Iterator<Alquiler> iteradorCliente = get(cliente).iterator();
		Alquiler alquilerAbierto1 = null;
		while (iteradorCliente.hasNext()) {
			Alquiler alquiler = iteradorCliente.next();
			if (alquiler.getCliente().equals(cliente) && alquiler.getFechaDevolucion() == null) {
				alquilerAbierto1 = alquiler;
			}
		}
		return alquilerAbierto1;
	}

	@Override
	public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		Alquiler clienteAbierto = getAlquilerAbierto(cliente);
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler de un cliente nulo.");
		}
		if (clienteAbierto == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler abierto para ese cliente.");
		}
		coleccionAlquileres.get(coleccionAlquileres.indexOf(clienteAbierto)).devolver(fechaDevolucion);
	}

	private Alquiler getAlquilerAbierto(Vehiculo vehiculo) {
		Iterator<Alquiler> iteradorVehiculo = get(vehiculo).iterator();
		Alquiler alquilerAbierto2 = null;
		while (iteradorVehiculo.hasNext()) {
			Alquiler alquiler = iteradorVehiculo.next();
			if (alquiler.getVehiculo().equals(vehiculo) && alquiler.getFechaDevolucion() == null) {
				alquilerAbierto2 = alquiler;
			}
		}
		return alquilerAbierto2;
	}

	@Override
	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler de un vehículo nulo.");
		}
		Alquiler vehiculoAbierto = getAlquilerAbierto(vehiculo);
		if (vehiculoAbierto == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler abierto para ese vehículo.");
		}
		vehiculoAbierto.devolver(fechaDevolucion);

	}

	@Override
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
		}
		if (!coleccionAlquileres.contains(alquiler)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
		coleccionAlquileres.remove(alquiler);
	}

	@Override
	public Alquiler buscar(Alquiler alquiler) {
		Alquiler alquilerBuscado;
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		}
		int indice = coleccionAlquileres.indexOf(alquiler);
		if (coleccionAlquileres.contains(alquiler)) {
			alquilerBuscado = coleccionAlquileres.get(indice);
		} else {
			alquilerBuscado = null;
		}
		return alquilerBuscado;
	}

}
