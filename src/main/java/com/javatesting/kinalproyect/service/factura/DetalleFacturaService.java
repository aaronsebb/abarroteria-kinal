package main.java.com.javatesting.kinalproyect.service.factura;

import javafx.collections.ObservableList;
import main.java.com.javatesting.kinalproyect.exception.factura.DetalleFacturaException;
import main.java.com.javatesting.kinalproyect.model.factura.DetalleFactura;
import main.java.com.javatesting.kinalproyect.repository.factura.DetalleFacturaRepository;

public class DetalleFacturaService {

    private final DetalleFacturaRepository detalleFacturaRepository = new DetalleFacturaRepository();

    public ObservableList<DetalleFactura> findAll() {
        return detalleFacturaRepository.findAll();
    }

    public boolean save(DetalleFactura detalleFactura) {
        validarDetalleFactura(detalleFactura);

        boolean guardado = detalleFacturaRepository.save(detalleFactura);
        if (!guardado) {
            throw new DetalleFacturaException("No se pudo guardar el detalle de la factura");
        }
        return true;
    }

    public boolean deleteById(String idDetalleFactura) {
        if (idDetalleFactura == null || idDetalleFactura.isBlank()) {
            throw new DetalleFacturaException("El id del detalle de factura es obligatorio");
        }

        boolean eliminado = detalleFacturaRepository.deleteById(idDetalleFactura);
        if (!eliminado) {
            throw new DetalleFacturaException("No se encontro el detalle de factura con id " + idDetalleFactura);
        }
        return true;
    }

    public boolean updateById(DetalleFactura detalleFactura) {
        validarDetalleFactura(detalleFactura);

        boolean actualizado = detalleFacturaRepository.updateById(detalleFactura);
        if (!actualizado) {
            throw new DetalleFacturaException(
                    "No se pudo actualizar el detalle de factura con id " + detalleFactura.getIdDetalleFactura());
        }
        return true;
    }

    private void validarDetalleFactura(DetalleFactura detalleFactura) {
        if (detalleFactura == null) {
            throw new DetalleFacturaException("El detalle de factura no puede ser nulo");
        }
        if (detalleFactura.getIdProducto() == null || detalleFactura.getIdProducto().isBlank()) {
            throw new DetalleFacturaException("El detalle de factura debe estar asociado a un producto");
        }
        if (detalleFactura.getIdFactura() == null || detalleFactura.getIdFactura().isBlank()) {
            throw new DetalleFacturaException("El detalle de factura debe estar asociado a una factura");
        }
        if (detalleFactura.getCantidadComprada() <= 0) {
            throw new DetalleFacturaException("La cantidad comprada debe ser mayor a cero");
        }
    }
}
