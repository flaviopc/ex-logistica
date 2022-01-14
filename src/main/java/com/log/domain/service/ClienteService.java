package com.log.domain.service;

import com.log.domain.exception.NegocioException;
import com.log.domain.model.Cliente;
import com.log.domain.repository.ClienteRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClienteService {
    private ClienteRepository clienteRepository;

    public Cliente buscar(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
    }

    @Transactional
    public Cliente salvar(Cliente cliente) {
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteAtual -> !clienteAtual.equals(cliente));
        if (emailEmUso) {
            throw new NegocioException("Email já cadastrado com outro usuário");
        }
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}
