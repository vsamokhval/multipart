package org.vss.springmvc.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.vss.springmvc.model.Phone;
import org.vss.springmvc.model.User;

import static java.util.Arrays.asList;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();

    {
        Phone phone1 = new Phone();
        phone1.setNumber("100100");
        phone1.setCompanyName("VSS Co");
        Phone phone2 = new Phone();
        phone2.setNumber("100101");
        phone2.setCompanyName("VSS Co");
        Phone phone3 = new Phone();
        phone2.setNumber("100201");
        phone2.setCompanyName("10G Co");
        User user1 = new User(1, "Name1", "Surname1", asList(phone1, phone3));
        User user2 = new User(2, "Name2", "Surname2", asList(phone2));

        users.add(user1);
        users.add(user2);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getAllUsers() {
        return users;
    }
}
