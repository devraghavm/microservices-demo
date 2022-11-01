package com.raghav.microservices.demo.elastic.query.service.business.impl;

import com.raghav.microservices.demo.elastic.query.service.business.QueryUserService;
import com.raghav.microservices.demo.elastic.query.service.dataaccess.entity.UserPermission;
import com.raghav.microservices.demo.elastic.query.service.dataaccess.repository.UserPermissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TwitterQueryUserService implements QueryUserService {
    private final static Logger log = LoggerFactory.getLogger(TwitterQueryUserService.class);
    private final UserPermissionRepository userPermissionRepository;

    public TwitterQueryUserService(UserPermissionRepository userPermissionRepository) {
        this.userPermissionRepository = userPermissionRepository;
    }

    @Override
    public Optional<List<UserPermission>> findAllPermissionsByUsername(String username) {
        log.info("Finding permissions by username {}", username);
        return userPermissionRepository.findPermissionsByUsername(username);
    }
}
