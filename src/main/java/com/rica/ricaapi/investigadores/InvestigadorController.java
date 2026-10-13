package com.rica.ricaapi.investigadores;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/investigadores")
@CrossOrigin(origins = "*")
public class InvestigadorController {

    private final InvestigadorService investigadorService;

    public InvestigadorController(InvestigadorService investigadorService) {
        this.investigadorService = investigadorService;
    }

    @GetMapping
    public List<InvestigadorResponse> listar() {
        return investigadorService.listarTodos().stream()
                .map(InvestigadorMapper::aResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public InvestigadorResponse buscarPorId(@PathVariable Long id) {
        Investigador investigador = investigadorService.buscarPorId(id);
        return InvestigadorMapper.aResponse(investigador);
    }

    @PostMapping
    public ResponseEntity<InvestigadorResponse> registrar(@Valid @RequestBody InvestigadorRequest request) {
        Investigador investigador = InvestigadorMapper.aEntidad(request);
        Investigador guardado = investigadorService.registrar(investigador.getNombreCompleto(), investigador.getCorreoInstitucional().valor(), investigador.getGrupoInvestigacion());
        InvestigadorResponse response = InvestigadorMapper.aResponse(guardado);
        return ResponseEntity
                .created(URI.create("/api/investigadores/" + guardado.getId()))
                .body(response);
    }
}