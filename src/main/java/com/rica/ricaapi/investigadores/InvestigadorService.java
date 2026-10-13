package com.rica.ricaapi.investigadores;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rica.ricaapi.compartido.CorreoDuplicadoException;
import com.rica.ricaapi.compartido.RecursoNoEncontradoException;

@Service
public class InvestigadorService {
    private final InvestigadorRepository investigadorRepository;
    private final InvestigadorFactory investigadorFactory;

    public InvestigadorService(InvestigadorRepository investigadorRepository, InvestigadorFactory investigadorFactory) {
        this.investigadorRepository = investigadorRepository;
        this.investigadorFactory = investigadorFactory;
    }

    public List<Investigador> listarTodos() {
        return investigadorRepository.findAll();
    }

    public Investigador buscarPorId(Long id) {
        return investigadorRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe un investigador con id " + id));
    }

    public Investigador registrar(String nombre, String correoInstitucional, String grupoInvestigacion) {
        Investigador investigador = investigadorFactory.crear(nombre, correoInstitucional, grupoInvestigacion);
        if (investigadorRepository.existsByCorreoInstitucional_Valor(investigador.getCorreoInstitucional().valor())) {
            throw new CorreoDuplicadoException(
                    "Ya existe un investigador registrado con el correo " + investigador.getCorreoInstitucional());
        }
        return investigadorRepository.save(investigador);
    }
}