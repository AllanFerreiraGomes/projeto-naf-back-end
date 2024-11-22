package com.example.projeto_naf_back.repository;

import com.example.projeto_naf_back.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByUsuarioId(Long usuarioId);
}
