package com.tiktac.toe.repository;

import com.tiktac.toe.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardrRepository extends JpaRepository<Board, Long> {
}
