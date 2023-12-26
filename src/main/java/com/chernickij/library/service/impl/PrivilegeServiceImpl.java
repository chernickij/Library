package com.chernickij.library.service.impl;

import com.chernickij.library.dto.SavePrivilegeDto;
import com.chernickij.library.exception.ResourceAlreadyExist;
import com.chernickij.library.exception.ResourceNotFoundException;
import com.chernickij.library.model.Privilege;
import com.chernickij.library.repository.PrivilegeRepository;
import com.chernickij.library.service.PrivilegeService;
import com.chernickij.library.dto.PrivilegeDto;
import com.chernickij.library.mapper.PrivilegeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.text.MessageFormat.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrivilegeServiceImpl implements PrivilegeService {

    private final PrivilegeRepository privilegeRepository;
    private final PrivilegeMapper privilegeMapper;

    @Override
    public List<PrivilegeDto> getAll(Integer offset, Integer limit) {
        log.debug("Executing the privilegeService.getAll method with offset = %d and limit = %d".formatted(offset, limit));

        Page<Privilege> pagedResult = privilegeRepository.findAll(PageRequest.of(offset, limit));

        return pagedResult.hasContent()
                ? pagedResult.getContent().stream().map(privilegeMapper::mapToPrivilegeDto).toList()
                : new ArrayList<>();
    }

    @Override
    public PrivilegeDto getById(long id) {
        log.debug("Executing the privilegeService.getById method with id {}", id);

        return privilegeRepository.findById(id)
                .map(privilegeMapper::mapToPrivilegeDto)
                .orElseThrow(() -> new ResourceNotFoundException(format("Privilege with id {0} not found ", id)));
    }

    @Override
    @Transactional
    public PrivilegeDto save(SavePrivilegeDto privilegeDto) {
        log.debug("Executing the privilegeService.save method for privilege with name: %s".formatted(privilegeDto.getName()));

        privilegeRepository.findByName(privilegeDto.getName())
                .ifPresent(privilege -> {
                    throw new ResourceAlreadyExist(MessageFormat.format("Privilege with name {0} already exist", privilege.getName()));
                });

        return privilegeMapper.mapToPrivilegeDto(privilegeRepository.save(Privilege.builder()
                .name(privilegeDto.getName())
                .created(new Date())
                .build()));
    }

    @Override
    @Transactional
    public PrivilegeDto update(long id, SavePrivilegeDto privilegeDto) {
        log.debug("Executing the privilegeService.save method for privilege with id: %s".formatted(id));

        Privilege privilege = privilegeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Privilege with id {0} not found ", id)));

        privilege.setName(privilegeDto.getName());
        privilege.setUpdated(new Date());

        return privilegeMapper.mapToPrivilegeDto(privilegeRepository.save(privilege));
    }

    @Override
    @Transactional
    public void delete(long id) {
        log.debug("Executing the privilegeService.delete method for privilege with id: %s".formatted(id));

        Privilege privilege = privilegeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Privilege with id {0} not found ", id)));
        privilegeRepository.delete(privilege);
    }
}
