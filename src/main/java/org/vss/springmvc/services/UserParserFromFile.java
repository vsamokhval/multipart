package org.vss.springmvc.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vss.springmvc.model.Phone;
import org.vss.springmvc.model.User;
import org.vss.springmvc.repositories.PhoneRepository;
import org.vss.springmvc.repositories.UserRepository;

@Service
public class UserParserFromFile {

    public static String UPLOAD_LOCATION = "C:/dev/";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    public List<User> parseFiles(List<String> fileNames) {
        String cvsSplitBy = ",";
        String line = "";
        List<User> users = new ArrayList<>();

        for (String fileName : fileNames) {
            File file = new File(UPLOAD_LOCATION + fileName);
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                while ((line = br.readLine()) != null) {
                    String[] userToParse = line.split(cvsSplitBy);
                    User user = new User();
                    user.setName(userToParse[0]);
                    user.setSurname(userToParse[1]);
                    for (int i = 2; i < userToParse.length; i = i + 2) {
                        Phone phone = new Phone();
                        phone.setNumber(userToParse[i]);
                        phone.setCompanyName(userToParse[i + 1]);
                        user.getPhoneNumbers().add(phone);
                    }

                    phoneRepository.saveAll(user.getPhoneNumbers());
                    users.add(user);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        userRepository.saveAll(users);

        return users;
    }
}
