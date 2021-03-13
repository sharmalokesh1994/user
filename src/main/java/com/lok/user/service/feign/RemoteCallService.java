package com.lok.user.service.feign;

import com.lok.user.vo.Department;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="DEPARTMENT-SERVICE")
public interface RemoteCallService {

    @RequestMapping(method= RequestMethod.GET, value="/dept/getDept/{id}")
    public Department getDepartment(@PathVariable Integer id);

}
