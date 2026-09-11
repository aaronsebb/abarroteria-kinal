package main.java.com.javatesting.kinalproyect.service.cliente;

import javafx.collections.ObservableList;
import main.java.com.javatesting.kinalproyect.exception.cliente.ClienteException;
import main.java.com.javatesting.kinalproyect.model.cliente.Cliente;
import main.java.com.javatesting.kinalproyect.repository.cliente.ClienteRepository;

public class ClienteService {

    private final ClienteRepository clienteRepository = new ClienteRepository();

    public ObservableList<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public boolean save(Cliente cliente) {
        validarCliente(cliente);

        boolean guardado = clienteRepository.save(cliente);
        if (!guardado) {
            throw new ClienteException("No se pudo guardar el cliente");
        }
        return true;
    }

    public boolean deleteById(String idCliente) {
        if (idCliente == null || idCliente.isBlank()) {
            throw new ClienteException("El id del cliente es obligatorio");
        }

        boolean eliminado = clienteRepository.deleteById(idCliente);
        if (!eliminado) {
            throw new ClienteException("No se encontro el cliente con id " + idCliente);
        }
        return true;
    }

    public boolean updateById(Cliente cliente) {
        validarCliente(cliente);

        boolean actualizado = clienteRepository.updateById(cliente);
        if (!actualizado) {
            throw new ClienteException("No se pudo actualizar el cliente con id " + cliente.getIdCliente());
        }
        return true;
    }

    private void validarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new ClienteException("El cliente no puede ser nulo");
        }
        if (cliente.getNombre() == null || cliente.getNombre().isBlank()) {
            throw new ClienteException("El nombre del cliente es obligatorio");
        }
        if (cliente.getApellido() == null || cliente.getApellido().isBlank()) {
            throw new ClienteException("El apellido del cliente es obligatorio");
        }
        if (cliente.getIdDireccion() == null || cliente.getIdDireccion().isBlank()) {
            throw new ClienteException("El cliente debe tener una direccion asociada");
        }
    }
}
