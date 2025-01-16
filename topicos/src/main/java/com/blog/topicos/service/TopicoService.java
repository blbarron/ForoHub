package com.blog.topicos.service;

import com.blog.topicos.entity.Topico;
import com.blog.topicos.repository.TopicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {
    private final TopicoRepository repository;

    public TopicoService(TopicoRepository repository) {
        this.repository = repository;
    }

    public List<Topico> listarTodos() {
        return repository.findAll();
    }

    public Topico obtenerPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Tópico no encontrado con ID: " + id));
    }

    public Topico crear(Topico topico) {
        return repository.save(topico);
    }

    public Topico actualizar(Long id, Topico topicoActualizado) {
        Topico topico = obtenerPorId(id);
        topico.setTitulo(topicoActualizado.getTitulo());
        topico.setMensaje(topicoActualizado.getMensaje());
        topico.setStatus(topicoActualizado.getStatus());
        topico.setAutor(topicoActualizado.getAutor());
        topico.setCurso(topicoActualizado.getCurso());
        return repository.save(topico);
    }

    public void eliminar(Long id) {
        Topico topico = obtenerPorId(id);
        repository.delete(topico);
    }
}
