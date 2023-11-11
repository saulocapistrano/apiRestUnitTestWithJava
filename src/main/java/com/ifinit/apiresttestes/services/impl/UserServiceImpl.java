package com.ifinit.apiresttestes.services.impl;

import com.ifinit.apiresttestes.domain.User;
import com.ifinit.apiresttestes.domain.dto.UserDTO;
import com.ifinit.apiresttestes.repositories.UserRepository;
import com.ifinit.apiresttestes.services.UserService;
import com.ifinit.apiresttestes.services.exceptions.DataIntegratyViolationException;
import com.ifinit.apiresttestes.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userRepository")
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found."));
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User create(UserDTO obj) {
        finfByEmail(obj);
        return repository.save(mapper.map(obj, User.class));
    }

    @Override
    public User update(UserDTO obj) {
        finfByEmail(obj);
        return repository.save(mapper.map(obj, User.class));
    }

    private void finfByEmail(UserDTO obj) {
        Optional<User> user = repository.findByMail(obj.getMail());
        if (user.isPresent()&& !user.get().getId().equals(obj.getId())) {
            throw new DataIntegratyViolationException("This email is already registered in the system.s");
        }

    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

}
