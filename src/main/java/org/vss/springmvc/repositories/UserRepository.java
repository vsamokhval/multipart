package org.vss.springmvc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.vss.springmvc.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
