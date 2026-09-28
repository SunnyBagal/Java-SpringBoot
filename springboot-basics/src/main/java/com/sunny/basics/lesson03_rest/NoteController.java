package com.sunny.basics.lesson03_rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/*
 * ============================================================
 *  LESSON 03 - File 2 of 2: a full CRUD REST controller
 * ============================================================
 *
 *  CRUD = Create, Read, Update, Delete. The standard REST design:
 *
 *   HTTP method  URL              Meaning               Success status
 *   -----------  ---------------  --------------------  ----------------
 *   POST         /api/notes       create a note         201 Created
 *   GET          /api/notes       list all notes        200 OK
 *   GET          /api/notes/1     get note with id 1    200 OK (404 if missing)
 *   PUT          /api/notes/1     replace note 1        200 OK (404 if missing)
 *   DELETE       /api/notes/1     delete note 1         204 No Content
 *   GET          /api/notes/search?q=java   filter      200 OK
 *
 *  NEW ANNOTATIONS:
 *   @PostMapping / @PutMapping / @DeleteMapping -> like @GetMapping, other HTTP verbs
 *   @RequestBody   -> turn the JSON in the request body into a Java object
 *   @PathVariable  -> value from the URL path  (/api/notes/{id})
 *   @RequestParam  -> value from the query string (?q=java)
 *   @ResponseStatus(HttpStatus.X) -> fixed status code for a method
 *   ResponseEntity<T> -> full control: status code + headers + body
 *
 *  STORAGE: For now, notes live in a Map in memory (gone on restart).
 *  In lesson 06 we'll use a real database.
 */
@RestController
@RequestMapping("/api/notes")
public class NoteController {

    // ConcurrentHashMap/AtomicLong are thread-safe versions of HashMap/long.
    // Needed because a web server handles many requests at the SAME time.
    private final Map<Long, Note> notes = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    // CREATE: POST /api/notes   body: {"title":"...","content":"..."}
    @PostMapping
    public ResponseEntity<Note> create(@RequestBody Note request) {
        long id = nextId.getAndIncrement();                   // 1, 2, 3...
        Note saved = new Note(id, request.title(), request.content());
        notes.put(id, saved);
        // 201 Created + a "Location" header telling the client where the new note lives
        return ResponseEntity.created(URI.create("/api/notes/" + id)).body(saved);
    }

    // READ ALL: GET /api/notes
    @GetMapping
    public List<Note> getAll() {
        return new ArrayList<>(notes.values());               // Spring -> JSON array
    }

    // READ ONE: GET /api/notes/1
    @GetMapping("/{id}")
    public ResponseEntity<Note> getById(@PathVariable Long id) {
        Note note = notes.get(id);
        if (note == null) {
            return ResponseEntity.notFound().build();         // 404 with empty body
        }
        return ResponseEntity.ok(note);                       // 200 with the note
    }

    // UPDATE: PUT /api/notes/1   body: {"title":"...","content":"..."}
    @PutMapping("/{id}")
    public ResponseEntity<Note> update(@PathVariable Long id, @RequestBody Note request) {
        if (!notes.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        Note updated = new Note(id, request.title(), request.content());
        notes.put(id, updated);
        return ResponseEntity.ok(updated);
    }

    // DELETE: DELETE /api/notes/1
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)                    // 204: success, nothing to return
    public void delete(@PathVariable Long id) {
        notes.remove(id);
    }

    // SEARCH: GET /api/notes/search?q=java
    @GetMapping("/search")
    public List<Note> search(@RequestParam("q") String query) {
        return notes.values().stream()
                .filter(n -> n.title().toLowerCase().contains(query.toLowerCase()))
                .toList();
    }
}

/*
 * ------------------------- OUTPUT -------------------------
 *  (-i shows the status line and headers, -X picks the HTTP method,
 *   -H sets a header, -d sends a request body)
 *
 * $ curl -i -X POST http://localhost:8080/api/notes \
 *        -H "Content-Type: application/json" \
 *        -d '{"title":"Learn Java","content":"OOP first"}'
 * HTTP/1.1 201
 * Location: /api/notes/1
 * Content-Type: application/json
 * ...
 * {"id":1,"title":"Learn Java","content":"OOP first"}
 *
 * $ curl -X POST http://localhost:8080/api/notes -H "Content-Type: application/json" \
 *        -d '{"title":"Learn Spring","content":"DI and REST"}'
 * {"id":2,"title":"Learn Spring","content":"DI and REST"}
 *
 * $ curl http://localhost:8080/api/notes
 * [{"id":1,"title":"Learn Java","content":"OOP first"},{"id":2,"title":"Learn Spring","content":"DI and REST"}]
 *
 * $ curl http://localhost:8080/api/notes/2
 * {"id":2,"title":"Learn Spring","content":"DI and REST"}
 *
 * $ curl -i http://localhost:8080/api/notes/99
 * HTTP/1.1 404
 *
 * $ curl -X PUT http://localhost:8080/api/notes/1 -H "Content-Type: application/json" \
 *        -d '{"title":"Learn Java 21","content":"Records too"}'
 * {"id":1,"title":"Learn Java 21","content":"Records too"}
 *
 * $ curl "http://localhost:8080/api/notes/search?q=spring"
 * [{"id":2,"title":"Learn Spring","content":"DI and REST"}]
 *
 * $ curl -i -X DELETE http://localhost:8080/api/notes/2
 * HTTP/1.1 204
 *
 * $ curl http://localhost:8080/api/notes
 * [{"id":1,"title":"Learn Java 21","content":"Records too"}]
 * ----------------------------------------------------------
 */
