package com.CleitonH.triagem_hospitalar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CleitonH.triagem_hospitalar.model.Paciente;
import com.CleitonH.triagem_hospitalar.repository.PacienteRepository;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    // Obter todos os pacientes
    @GetMapping
    public List<Paciente> obterTodosPacientes() {
        return pacienteRepository.findAll();
    }

    // Obter paciente por id
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> obterPaciente(@PathVariable Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        return paciente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Adicionar paciente
    @PostMapping
    public ResponseEntity<Paciente> adicionarPaciente(@RequestBody Paciente paciente) {
        Paciente novoPaciente = pacienteRepository.save(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPaciente);
    }

    // Editar paciente
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> editarPaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        if (!pacienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        paciente.setId(id);
        Paciente pacienteEditado = pacienteRepository.save(paciente);
        return ResponseEntity.ok(pacienteEditado);
    }

    // Excluir paciente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPaciente(@PathVariable Long id) {
        if (!pacienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pacienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
