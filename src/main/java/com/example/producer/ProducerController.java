package com.example.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producer")
public class ProducerController {
    private final ProducerService producerService;


    @Autowired
    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addProducer(@RequestBody Producer producer) {
        producerService.addProducer(producer);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteProducer(@PathVariable Long id) {
        producerService.deleteProducer(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateProducer(@RequestBody Producer producer) {
        producerService.updateProducer(producer);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Producer getProducer(@PathVariable Long id) {
        return producerService.getProducer(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Producer> getProducer() {
        return producerService.listProducer();
    }

}
