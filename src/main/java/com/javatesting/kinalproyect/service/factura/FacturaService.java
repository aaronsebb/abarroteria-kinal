package main.java.com.javatesting.kinalproyect.service.factura;

import java.util.Date;
import javafx.collections.ObservableList;
import main.java.com.javatesting.kinalproyect.exception.factura.FacturaException;
import main.java.com.javatesting.kinalproyect.model.factura.Factura;
import main.java.com.javatesting.kinalproyect.repository.factura.FacturaRepository;

public class FacturaService {

    private final FacturaRepository facturaRepository = new FacturaRepository();

    public ObservableList<Factura> findAll() {
        return facturaRepository.findAll();
    }

    public boolean save(Factura factura) {
        validarFactura(factura);

        boolean guardado = facturaRepository.save(factura);
        if (!guardado) {
            throw new FacturaException("No se pudo guardar la factura");
        }
        return true;
    }

    public boolean deleteById(String idFactura) {
        if (idFactura == null || idFactura.isBlank()) {
            throw new FacturaException("El id de la factura es obligatorio");
        }

        boolean eliminado = facturaRepository.deleteById(idFactura);
        if (!eliminado) {
            throw new FacturaException("No se encontro la factura con id " + idFactura);
        }
        return true;
    }

    public boolean updateById(Factura factura) {
        validarFactura(factura);

        boolean actualizado = facturaRepository.updateById(factura);
        if (!actualizado) {
            throw new FacturaException("No se pudo actualizar la factura con id " + factura.getIdFactura());
        }
        return true;
    }

    private void validarFactura(Factura factura) {
        if (factura == null) {
            throw new FacturaException("La factura no puede ser nula");
        }
        if (factura.getIdCliente() == null || factura.getIdCliente().isBlank()) {
            throw new FacturaException("La factura debe estar asociada a un cliente");
        }
        if (factura.getMonto() <= 0) {
            throw new FacturaException("El monto de la factura debe ser mayor a cero");
        }
        if (factura.getFecha() == null) {
            throw new FacturaException("La fecha de la factura es obligatoria");
        }
        if (factura.getFecha().after(new Date())) {
            throw new FacturaException("La fecha de la factura no puede ser futura");
        }
    }
}
