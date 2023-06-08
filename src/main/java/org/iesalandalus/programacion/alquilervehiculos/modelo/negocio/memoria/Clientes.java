package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria;

import java.util.ArrayList;
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;


public class Clientes implements IClientes {
	
	//ARRAY LIST

	private List<Cliente> coleccionClientes;

	public Clientes() {
		coleccionClientes = new ArrayList<>();
	}
	
	@Override
	public List<Cliente> get() {
		return new ArrayList<>(coleccionClientes);
	}

	@Override
	public int getCantidad() {
		return coleccionClientes.size();
	}

	@Override
	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
		}

		if (coleccionClientes.contains(cliente)) {
			throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese DNI.");
		}

		coleccionClientes.add(cliente);
	}

	@Override
	public Cliente buscar(Cliente cliente) {
		Cliente clienteBuscado;
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede buscar un cliente nulo.");
		}
		int indice = coleccionClientes.indexOf(cliente);
		if (indice != -1) {
			clienteBuscado = coleccionClientes.get(indice);
		} else {
			clienteBuscado = null;
		}
		return clienteBuscado;
	}

	@Override
	public void borrar(Cliente cliente) throws OperationNotSupportedException {

		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");
		}
		if (!coleccionClientes.contains(cliente)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}
		coleccionClientes.remove(cliente);
	}

	@Override
	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {

		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede modificar un cliente nulo.");
		}
		Cliente clienteModif = buscar(cliente);
		if (clienteModif == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		} else {
			if (nombre != null && !nombre.trim().isEmpty()) {
				clienteModif.setNombre(nombre);
			}

			if (telefono != null && !telefono.trim().isEmpty()) {
				clienteModif.setTelefono(telefono);
			}

	}
	}

}
