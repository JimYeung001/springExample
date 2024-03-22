package com.eazy.school.service;

import com.eazy.school.config.EzySchoolConstants;
import com.eazy.school.model.Contact;
import com.eazy.school.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    /**
     * save msg
     *
     * @param contact
     * @return true if success otherwise false
     */
    public boolean saveMessageDetails(Contact contact) {
        log.info(contact.toString());
        contact.setStatus(EzySchoolConstants.OPEN);
        contact.setCreatedBy(EzySchoolConstants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());
        int result = contactRepository.saveContactMsg(contact);
        return result > 0;
    }

    public List<Contact> findMsgWithOpenStatus() {
        List<Contact> contacts = contactRepository.findMsgsWithStatus(EzySchoolConstants.OPEN);
        return contacts;
    }

    public boolean updateMsgStatus(int id, String updatedBy) {
        int result = contactRepository.updateMsgStatus(id, EzySchoolConstants.CLOSE, updatedBy);
        return result > 0;
    }
}
