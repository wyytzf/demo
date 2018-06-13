package com.example.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducerServiceImpl implements ProducerService {
    @Autowired
    private ProducerRepository producerRepository;

    @Override
    public void addProducer(Producer producer) {
        producerRepository.save(producer);
    }

    @Override
    public void deleteProducer(Long id) {
        producerRepository.deleteById(id);
    }

    @Override
    public void updateProducer(Producer producer) {
        producerRepository.save(producer);
    }

    @Override
    public Producer getProducer(Long id) {
        return producerRepository.getOne(id);
    }

    @Override
    public List<Producer> listProducer() {
        return producerRepository.findAll();
    }
}
