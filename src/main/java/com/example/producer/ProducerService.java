package com.example.producer;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProducerService {
    void addProducer(Producer producer);

    void deleteProducer(Long id);

    void updateProducer(Producer producer);

    Producer getProducer(Long id);

    List<Producer> listProducer();
}
