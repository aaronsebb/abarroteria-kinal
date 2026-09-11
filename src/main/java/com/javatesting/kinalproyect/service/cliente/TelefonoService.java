package main.java.com.javatesting.kinalproyect.service.cliente;

import javafx.collections.ObservableList;
import main.java.com.javatesting.kinalproyect.exception.cliente.TelefonoException;
import main.java.com.javatesting.kinalproyect.model.cliente.Telefono;
import main.java.com.javatesting.kinalproyect.repository.cliente.TelefonoRepository;

public class TelefonoService {

    private final TelefonoRepository telefonoRepository = new TelefonoRepository();

    public ObservableList<Telefono> findAll() {
        return telefonoRepository.findAll();
    }

    public boolean save(Telefono telefono) {
        validarTelefono(telefono);

        boolean guardado = telefonoRepository.save(telefono);
        if (!guardado) {
            throw new TelefonoException("No se pudo guardar el telefono");
        }
        return true;
    }

    public boolean deleteById(String idTelefono) {
        if (idTelefono == null || idTelefono.isBlank()) {
            throw new TelefonoException("El id del telefono es obligatorio");
        }

        boolean eliminado = telefonoRepository.deleteById(idTelefono);
        if (!eliminado) {
            throw new TelefonoException("No se encontro el telefono con id " + idTelefono);
        }
        return true;
    }

    public boolean updateById(Telefono telefono) {
        validarTelefono(telefono);

        boolean actualizado = telefonoRepository.updateById(telefono);
        if (!actualizado) {
            throw new TelefonoException("No se pudo actualizar el telefono con id " + telefono.getIdTelefono());
        }
        return true;
    }

    private void validarTelefono(Telefono telefono) {
        if (telefono == null) {
            throw new TelefonoException("El telefono no puede ser nulo");
        }
        if (telefono.getIdCliente() == null || telefono.getIdCliente().isBlank()) {
            throw new TelefonoException("El telefono debe estar asociado a un cliente");
        }
        if (telefono.getTelefono() == null || telefono.getTelefono().isBlank()) {
            throw new TelefonoException("El numero de telefono es obligatorio");
        }
        if (!telefono.getTelefono().matches("\\d{8}")) {
            throw new TelefonoException("El numero de telefono debe tener 8 digitos");
        }
    }
}
