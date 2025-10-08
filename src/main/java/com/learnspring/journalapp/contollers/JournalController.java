package com.learnspring.journalapp.contollers;

import com.learnspring.journalapp.JournalAppApplication;
import com.learnspring.journalapp.entities.JournalEntity;
import com.learnspring.journalapp.services.JournalService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;



@RestController
@RequestMapping("journal")
public class JournalController {

    JournalService journalService;

    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping("/{id}")
    public JournalEntity getJournal(@PathVariable("id") ObjectId id) {
        return journalService.getJournalById(id);
    }

    @PostMapping
    public JournalEntity addJournal(@RequestBody JournalEntity journal) {
        journal.setCreatedAt(LocalDateTime.now());
        journal.setUpdatedAt(LocalDateTime.now());
        return journalService.createJournal(journal);
    }


    @PutMapping("/{id}")
    public JournalEntity upadteJournal(@PathVariable("id") ObjectId id, @RequestBody JournalEntity journal) {
        journal.setUpdatedAt(LocalDateTime.now());
        return journalService.updateJournal(id,journal);
    }


    @DeleteMapping("/{id}")
    public boolean deleteJournal(@PathVariable("id") ObjectId id) {
        journalService.deleteJournal(id);
        return true;
    }


    @GetMapping()
    public List<JournalEntity> getAllJournals(){
        return journalService.getAllJournals();
    }
}
