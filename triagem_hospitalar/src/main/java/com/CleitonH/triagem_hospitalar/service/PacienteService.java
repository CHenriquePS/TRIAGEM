package com.CleitonH.triagem_hospitalar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CleitonH.triagem_hospitalar.model.Paciente;
import com.CleitonH.triagem_hospitalar.repository.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> obterPacientePorId(Long id) {
        return pacienteRepository.findById(id);
    }

    public Paciente salvarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Paciente atualizarPaciente(Long id, Paciente paciente) {
        paciente.setId(id);
        return pacienteRepository.save(paciente);
    }

    public void excluirPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }
}
