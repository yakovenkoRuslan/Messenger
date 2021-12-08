package com.chess.service.interfaces;

import com.chess.dao.entity.messanger.StatusEntity;

public interface StatusService {
    StatusEntity findStatusById(Long id);
}
