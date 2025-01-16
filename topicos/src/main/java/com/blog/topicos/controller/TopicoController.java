package com.blog.topicos.controller;

import com.blog.topicos.entity.Topico;
import com.blog.topicos.service.TopicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    private final TopicoService service;

    public TopicoController(TopicoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Map<String, List<Topico>>> listarTodos() {
        List<Topico> topicos = service.listarTodos();
        Map<String, List<Topico>> response = Map.of("content", topicos);
        return ResponseEntity.ok(response);
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<Topico> obtenerPorId(@PathVariable Long id) {
        Topico topico = service.obtenerPorId(id);
        return new ResponseEntity<>(topico, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Topico> crear(@RequestBody Topico topico) {
        Topico nuevoTopico = service.crear(topico);
        return new ResponseEntity<>(nuevoTopico, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topico> actualizar(@PathVariable Long id, @RequestBody Topico topico) {
        Topico topicoActualizado = service.actualizar(id, topico);
        return new ResponseEntity<>(topicoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
