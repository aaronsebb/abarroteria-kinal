package main.java.com.javatesting.kinalproyect.service.usuario;

import javafx.collections.ObservableList;
import main.java.com.javatesting.kinalproyect.exception.usuario.RolException;
import main.java.com.javatesting.kinalproyect.model.usuario.Rol;
import main.java.com.javatesting.kinalproyect.repository.usuario.RolRepository;

public class RolService {

    private final RolRepository rolRepository = new RolRepository();

    public ObservableList<Rol> findAll() {
        return rolRepository.findAll();
    }

    public Rol findById(int idRol) {
        if (idRol <= 0) {
            throw new RolException("El id del rol debe ser un valor positivo");
        }

        Rol rol = rolRepository.findById(idRol);
        if (rol == null) {
            throw new RolException("No se encontro el rol con id " + idRol);
        }
        return rol;
    }
}
