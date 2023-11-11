package com.ifinit.apiresttestes.services;

import com.ifinit.apiresttestes.domain.User;
import com.ifinit.apiresttestes.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    public List<User> findAll();
    User create(UserDTO obj);
    User update(UserDTO obj);
    void delete(Integer id);

}
