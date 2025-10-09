package com.learnspring.journalapp.services;
import java.util.List;
import com.learnspring.journalapp.entities.JournalEntity;
import com.learnspring.journalapp.entities.UserEntity;
import com.learnspring.journalapp.repository.JournalRepository;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;


@Component
public class JournalService {
    private final JournalRepository journalRepository;
    private  final UserService userService;
    public JournalService(JournalRepository journalRepository, UserService userService) {
        this.journalRepository = journalRepository;
        this.userService = userService;
    }

    // Create journal
    public JournalEntity createJournal(JournalEntity journal, String username) {
        UserEntity user=userService.findByUsername(username);
        JournalEntity userJournal=journalRepository.save(journal);
        user.getJournals().add(userJournal);
        userService.saveUser(user);
        return userJournal;
    }

    // get single journal
    public JournalEntity getJournalById(ObjectId id) {
        return journalRepository.findById(id).orElse(null);
    }

    // get all journal
    public List<JournalEntity> getAllJournals() {
        return journalRepository.findAll();
    }

    // update journal
    public JournalEntity updateJournal(ObjectId id, JournalEntity journal) {
        JournalEntity journalEntity = journalRepository.findById(id).orElse(null);
        if (journalEntity != null) {
            journalEntity.setContent(journal.getContent()!=null&&!journal.getContent().equals("")?journal.getContent():journalEntity.getContent());
            journalEntity.setTitle(journal.getTitle()!=null&&!journal.getTitle().equals("")?journal.getTitle():journalEntity.getTitle());
        }
        return journalRepository.save(journalEntity);
    }

    // delete journal
    public void deleteJournal(ObjectId id,String username) {
        UserEntity user=userService.findByUsername(username);
        user.getJournals().remove(journalRepository.findById(id).orElse(null));
        userService.saveUser(user);
        journalRepository.deleteById(id);
    }


}
