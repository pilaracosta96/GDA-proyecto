package com.gda.java.adquisiciones.services;

import com.gda.java.adquisiciones.entity.TipoMaterial;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TipoMaterialService {
    public String getTipoMaterial(TipoMaterial tipoMaterial, boolean obtenerNombre) {
        if (obtenerNombre) {
            return tipoMaterial.getNombreTipoMaterial();
        } else {
            return String.valueOf(tipoMaterial.getIdTipoMaterial());
        }
    }

}
