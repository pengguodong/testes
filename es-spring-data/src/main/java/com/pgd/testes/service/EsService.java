package com.pgd.testes.service;

import com.pgd.testes.dao.EsRepository;
import com.pgd.testes.model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsService {
    @Autowired
    EsRepository esRepository;

    public List<Hotel> list(String title) {
        return esRepository.findByTitleLike(title);
    }

}
