package org.vss.springmvc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vss.springmvc.model.Phone;
import org.vss.springmvc.model.User;
import org.vss.springmvc.repositories.PhoneRepository;
import org.vss.springmvc.repositories.UserRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserParserFromFile {

    private static final Logger logger = LoggerFactory.getLogger(UserParserFromFile.class);

    public static String UPLOAD_LOCATION = "C:/dev/";

    @Autowired
    @Qualifier("phoneRepository")
    private PhoneRepository phoneRepository;

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

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
                        phone.setUser(user);
                        user.getPhoneNumbers().add(phone);
                    }

                    User save = userRepository.save(user);
                    phoneRepository.saveAll(user.getPhoneNumbers());
                    users.add(save);
                }
            } catch (IOException e) {
                logger.error("Error: ", e);
            }
        }

        return users;
    }
}
