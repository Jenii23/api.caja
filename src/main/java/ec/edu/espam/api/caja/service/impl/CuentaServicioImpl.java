package ec.edu.espam.api.caja.service.impl;
import ec.edu.espam.api.caja.domain.Cuenta;
import ec.edu.espam.api.caja.repository.CuentaRepositorio;
import ec.edu.espam.api.caja.service.CuentaServicio;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CuentaServicioImpl implements CuentaServicio{
    private final CuentaRepositorio cuentaRepositorio;

    @Override
    public List<Cuenta> obtenerTodos() {
        return cuentaRepositorio.findAll();
    }

    @Override
    public Cuenta guardar(Cuenta cuenta) {
        return cuentaRepositorio.save(cuenta);
    }

    @Override
    public Cuenta actualizar(long id, Cuenta cuenta) {
        Cuenta DatosExistentes = cuentaRepositorio.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encuentra la cuenta con el id: " + id));
        DatosExistentes.setNumero(cuenta.getNumero());
        DatosExistentes.setTipo(cuenta.getTipo());
        DatosExistentes.setBalanceInicial(cuenta.getBalanceInicial());
        DatosExistentes.setSaldo(cuenta.getSaldo());
        DatosExistentes.setEstado(cuenta.getEstado());
        return cuentaRepositorio.save(DatosExistentes);
    }
}
