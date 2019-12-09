package org.vss.springmvc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.vss.springmvc.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
