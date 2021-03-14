package com.example.api.controller;


import com.example.api.Request;
import com.example.api.dao.NoteJdbc;
import com.example.api.model.Note;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
public class NoteController {
    private final NoteJdbc noteJdbc;

    public NoteController(NoteJdbc noteJdbc) {
        this.noteJdbc = noteJdbc;
    }

    @GetMapping("/notes")
    public ArrayList<Note> getNotes(){
        return noteJdbc.getNotes();
    }

    @GetMapping("/notes/{id}")
    public Note getNote(@PathVariable int id){
        return noteJdbc.getNote(id);
    }

    @DeleteMapping("/notes/{id}")
    public void delGroupById(@PathVariable int id) {
        noteJdbc.deleteNote(id);
    }

    @PostMapping("/notes")
    public Note insertNote(@RequestBody Request request) {
       return noteJdbc.insertNote(request.id, request.title, request.content);
    }

    @PutMapping("/notes/{id}")
    public void updStudById(@PathVariable int id, @RequestBody Request request) {
        noteJdbc.updateNote(id, request.title, request.content);
    }
}
