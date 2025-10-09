package com.learnspring.journalapp.contollers;

import com.learnspring.journalapp.JournalAppApplication;
import com.learnspring.journalapp.entities.JournalEntity;
import com.learnspring.journalapp.services.JournalService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getJournal(@PathVariable("id") ObjectId id) {
        JournalEntity journal = journalService.getJournalById(id);

        if (journal == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Journal not found for ID: " + id);
        }

        return ResponseEntity.ok(journal);
    }


    @PostMapping("/user/{username}")
    public ResponseEntity<JournalEntity> addJournal(@PathVariable String username,@RequestBody JournalEntity journal) {
        journal.setCreatedAt(LocalDateTime.now());
        journal.setUpdatedAt(LocalDateTime.now());
        JournalEntity journalEntity=journalService.createJournal(journal,username);
        return ResponseEntity.status(HttpStatus.CREATED).body(journalEntity);
    }


    @PutMapping("/{id}")
    public ResponseEntity<JournalEntity> upadteJournal(@PathVariable("id") ObjectId id, @RequestBody JournalEntity journal) {
        journal.setUpdatedAt(LocalDateTime.now());
        JournalEntity journalEntity=journalService.updateJournal(id,journal);
        return ResponseEntity.ok(journalEntity);
    }


    @DeleteMapping("/{id}/user/{username}")
    public ResponseEntity<Void> deleteJournal(@PathVariable ObjectId id,
                                              @PathVariable("username") String username) {
        journalService.deleteJournal(id,username);
        return ResponseEntity.noContent().build();
    }


    @GetMapping()
    public ResponseEntity<List<JournalEntity>> getAllJournals(){
        List<JournalEntity> journalEntities=journalService.getAllJournals();
        if(!journalEntities.isEmpty()){
            return ResponseEntity.ok(journalEntities);
        }
        return ResponseEntity.noContent().build();
    }
}
