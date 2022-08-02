package com.pgd.testes.dao;

import com.pgd.testes.model.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EsRepository extends CrudRepository<Hotel, String> {

    List<Hotel> findByTitleLike(String title);
}
