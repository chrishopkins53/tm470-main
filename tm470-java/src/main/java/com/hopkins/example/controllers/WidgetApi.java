package com.hopkins.example.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hopkins.example.models.Widget;
import com.hopkins.example.repositories.WidgetRepo;

@RestController
@RequestMapping("/api")
public class WidgetApi {

    private final WidgetRepo widgetRepo;

    public WidgetApi(WidgetRepo widgetRepo) {
        this.widgetRepo = widgetRepo;
    }

    @GetMapping("/widget/{id}")
    public ResponseEntity<Widget> getWidget(@PathVariable int id) {

        if (widgetRepo.find(id) != null) {
            return ResponseEntity.ok(widgetRepo.find(id));
        } else  {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/widget/add")
    public ResponseEntity<String> addWidget(@RequestBody Widget newWidget){
        if (widgetRepo.add(newWidget)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }

    }
}