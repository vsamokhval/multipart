package org.vss.springmvc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.vss.springmvc.model.Phone;

public interface PhoneRepository extends CrudRepository<Phone, Integer> {
}
