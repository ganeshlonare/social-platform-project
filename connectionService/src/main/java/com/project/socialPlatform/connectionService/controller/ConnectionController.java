package com.project.socialPlatform.connectionService.controller;

import com.project.socialPlatform.connectionService.entity.Person;
import com.project.socialPlatform.connectionService.service.ConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class ConnectionController {

    private final ConnectionService connectionService;

    @GetMapping("/{userId}/first-degree")
    public ResponseEntity<List<Person>> getFirstDegreeConnections(@PathVariable Long userId){
        List<Person> personList = connectionService.getFirstDegreeConnectionsOfUser(userId);
        return ResponseEntity.ok(personList);
    }
}
