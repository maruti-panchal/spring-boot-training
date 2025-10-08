package com.learnspring.journalapp.contollers;

import com.learnspring.journalapp.JournalAppApplication;
import com.learnspring.journalapp.entities.JournalEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("journal")
public class JournalController {
    Map<Integer, JournalEntity> journals = new HashMap<>();

    @GetMapping("/{id}")
    public JournalEntity getJournal(@PathVariable("id") int id) {
        return journals.get(id);
    }

    @PostMapping
    public boolean addJournal(@RequestBody JournalEntity journal) {
        journals.put(journal.getId(), journal);
        return true;
    }
    @PutMapping("/{id}")
    public void upadteJournal(@PathVariable("id") int id, @RequestBody JournalEntity journal) {
        journals.put(id,journal);
    }


    @DeleteMapping("/{id}")
    public void deleteJournal(@PathVariable("id") int id) {
        journals.remove(id);
    }


    @GetMapping()
    public ArrayList<JournalEntity> getAllJournals(){
        return new ArrayList<>(journals.values());
    }
}
