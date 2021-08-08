package com.example.boot.DAO;

import com.example.boot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends CrudRepository<User, Long> {

    List<User> findAll();

    public User save(User user);

    public User findById(long id);

//    public void deleteById(long id);
User getUserByUsername(String username);
//    public User findByUsername(String username);

}
