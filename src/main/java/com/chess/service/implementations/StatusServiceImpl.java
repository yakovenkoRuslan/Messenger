package com.chess.service.implementations;

import com.chess.dao.entity.messanger.StatusEntity;
import com.chess.dao.repository.StatusRepository;
import com.chess.service.interfaces.StatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class StatusServiceImpl implements StatusService {

    final StatusRepository statusRepository;

    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    @Transactional
    public StatusEntity findStatusById(Long id) {
        return statusRepository.findStatusEntityById(id);
    }

    @Override
    @Transactional
    public StatusEntity findStatusByName(String name) {
        return statusRepository.findStatusEntityByName(name);
    }
}
