package com.chess.dao.repository;

import com.chess.dao.entity.messanger.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<StatusEntity, Long> {

    StatusEntity findStatusEntityById(Long id);

    StatusEntity findStatusEntityByName(String name);
}
