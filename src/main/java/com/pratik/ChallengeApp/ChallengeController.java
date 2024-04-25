package com.pratik.ChallengeApp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class ChallengeController {

    private ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }





    //method to return all challenges
    @GetMapping("/challenges")
    public ResponseEntity<List<Challenge>> getAllChallenges() {
        return new ResponseEntity<>(challengeService.getAllChallenges(), HttpStatus.OK);
    }

    //adding challenge to the list

    @PostMapping("/challenges")
    public ResponseEntity<String> addChallenge(@RequestBody Challenge challenge) {

        boolean isChallengeAdded = challengeService.addChallenge(challenge);
        if (isChallengeAdded) {

            return new ResponseEntity<>("challenge added successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("challenge not added", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/challenges/{month}")
    //responseentity is used to handle the response errors
    public ResponseEntity<Challenge> getChallenge(@PathVariable String month) {
        Challenge challenge = challengeService.getChallenge(month);
        if(challenge != null) return new ResponseEntity<>(challenge, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/challenges/{id}")
    public ResponseEntity<String> updateChallenge(@PathVariable Long id,@RequestBody Challenge updatedChallenge) {
        boolean isChallengeUpdated = challengeService.updatedChallenge(id, updatedChallenge);
        if (isChallengeUpdated)
            return new ResponseEntity<>("challenge updated successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("challenge not updated", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/challenges/{id}")
    public ResponseEntity<String> deleteChallenge(@PathVariable Long id) {
        boolean isChallengeDeleted = challengeService.deleteChallenge(id);
        if(isChallengeDeleted)
            return new ResponseEntity<>("challenge deleted successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("challenge did not delete", HttpStatus.NO_CONTENT);
    }

}
