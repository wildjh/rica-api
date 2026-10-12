package com.rica.ricaapi;

import org.springframework.stereotype.Service;

@Service
public class SaludoInstitucionalService {

    public String mensajeDeBienvenida(){
        return "RICA está en la línea - Red de Investigación y Colaboración Académica.";
    }
}
