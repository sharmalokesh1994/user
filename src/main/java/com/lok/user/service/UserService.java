package com.lok.user.service;

import com.lok.user.entities.UserEntity;
import com.lok.user.repo.UserRepository;
import com.lok.user.service.feign.RemoteCallService;
import com.lok.user.vo.Department;
import com.lok.user.vo.UserDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RemoteCallService remoteCallService;

    public UserEntity saveUser(UserEntity userEntity) {
         return userRepository.save(userEntity);
    }

    public UserDepartment getUser(int id) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
        if(!optionalUserEntity.isPresent()) {
            return null;
        }
        UserEntity userEntity = optionalUserEntity.get();

        Department department = remoteCallService.getDepartment(userEntity.getDepId());

        return UserDepartment.builder().userEntity(userEntity).department(department).build();
    }
}
