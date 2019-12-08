package org.vss.springmvc.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.vss.springmvc.model.User;
import org.vss.springmvc.repositories.UserRepository;
import org.vss.springmvc.services.UserParserFromFile;
import org.vss.springmvc.services.UserService;

import static org.vss.springmvc.services.UserParserFromFile.UPLOAD_LOCATION;

@Controller
public class FileUploadController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserParserFromFile parser;

    @RequestMapping(value = "/multiUpload", method = RequestMethod.GET)
    public String getMultiUploadPage(@ModelAttribute("model") ModelMap model) {
        return "multiFileUploader";
    }

    @RequestMapping(value = "/multiUpload", method = RequestMethod.POST)
    public String multiFileUpload(@RequestParam("uploadingFiles") MultipartFile[] uploadingFiles,
                                  @ModelAttribute("model") ModelMap model) throws IOException {

        System.out.println("Fetching files");
        List<String> fileNames = new ArrayList<>();
        for (MultipartFile uploadedFile : uploadingFiles) {
            FileCopyUtils.copy(uploadedFile.getBytes(), new File(UPLOAD_LOCATION + uploadedFile.getOriginalFilename()));
            fileNames.add(uploadedFile.getOriginalFilename());
        }

        List<User> parsedUsers = parser.parseFiles(fileNames);
        userService.getAllUsers().addAll(parsedUsers);

        model.addAttribute("fileNames", fileNames);
        return "multiSuccess";
    }

}
