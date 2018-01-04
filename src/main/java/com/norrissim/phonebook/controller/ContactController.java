package com.norrissim.phonebook.controller;

import com.norrissim.phonebook.entity.Contact;
import com.norrissim.phonebook.repository.RoleRepository;
import com.norrissim.phonebook.repository.UserRepository;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.norrissim.phonebook.utils.State.populateModel;

/**
 * Created by User on 31.12.2017.
 */

@RestController
public class ContactController {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/contacts/all", method = RequestMethod.GET)
    public List<Contact> getContacts() {
        Contact contact = new Contact("Nikolay", "+375292771413", "https://pp.userapi.com/c629221/v629221411/17d7b/sP7i2kZ78GQ.jpg");
        contact.setId(0L);
        List<Contact> list = new ArrayList<>();
        list.add(contact);
        return list;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {
        populateModel(model, request);
        return "index";
    }
}
