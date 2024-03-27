package com.eazy.school.service;

import com.eazy.school.config.EzySchoolConstants;
import com.eazy.school.model.Contact;
import com.eazy.school.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        Contact result = contactRepository.save(contact);
        return result.getContactId() > 0;
    }

    public List<Contact> findMsgWithOpenStatus() {
        return contactRepository.findByStatus(EzySchoolConstants.OPEN);
    }

    public boolean updateMsgStatus(int id, String updatedBy) {
        Optional<Contact> contact = contactRepository.findById(id);
        contact.ifPresent(contact1 -> {
            contact1.setStatus(EzySchoolConstants.CLOSE);
            contact1.setUpdatedBy(updatedBy);
            contact1.setUpdatedAt(LocalDateTime.now());
        });
        contact.ifPresent(value -> contactRepository.save(value));
        return contact.isPresent();
    }
}
