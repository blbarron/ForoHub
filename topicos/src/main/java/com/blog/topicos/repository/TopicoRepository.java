package com.blog.topicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.topicos.entity.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
}

