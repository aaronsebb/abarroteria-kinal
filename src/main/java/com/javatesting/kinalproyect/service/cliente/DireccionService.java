package main.java.com.javatesting.kinalproyect.service.cliente;

import javafx.collections.ObservableList;
import main.java.com.javatesting.kinalproyect.exception.cliente.DireccionException;
import main.java.com.javatesting.kinalproyect.model.cliente.Direccion;
import main.java.com.javatesting.kinalproyect.repository.cliente.DireccionRepository;

public class DireccionService {

    private final DireccionRepository direccionRepository = new DireccionRepository();

    public ObservableList<Direccion> findAll() {
        return direccionRepository.findAll();
    }

    public boolean save(Direccion direccion) {
        validarDireccion(direccion);

        boolean guardado = direccionRepository.save(direccion);
        if (!guardado) {
            throw new DireccionException("No se pudo guardar la direccion");
        }
        return true;
    }

    public boolean deleteById(String idDireccion) {
        if (idDireccion == null || idDireccion.isBlank()) {
            throw new DireccionException("El id de la direccion es obligatorio");
        }

        boolean eliminado = direccionRepository.deleteById(idDireccion);
        if (!eliminado) {
            throw new DireccionException("No se encontro la direccion con id " + idDireccion);
        }
        return true;
    }

    public boolean updateById(Direccion direccion) {
        validarDireccion(direccion);

        boolean actualizado = direccionRepository.updateById(direccion);
        if (!actualizado) {
            throw new DireccionException("No se pudo actualizar la direccion con id " + direccion.getIdDireccion());
        }
        return true;
    }

    private void validarDireccion(Direccion direccion) {
        if (direccion == null) {
            throw new DireccionException("La direccion no puede ser nula");
        }
        if (direccion.getCiudad() == null || direccion.getCiudad().isBlank()) {
            throw new DireccionException("La ciudad es obligatoria");
        }
        if (direccion.getZona() == null || direccion.getZona().isBlank()) {
            throw new DireccionException("La zona es obligatoria");
        }
        if (direccion.getCalle() == null || direccion.getCalle().isBlank()) {
            throw new DireccionException("La calle es obligatoria");
        }
    }
}
