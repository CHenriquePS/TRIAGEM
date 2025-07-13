package com.CleitonH.triagem_hospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CleitonH.triagem_hospitalar.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
