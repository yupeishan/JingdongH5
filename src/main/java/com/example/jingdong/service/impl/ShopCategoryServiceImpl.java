package com.example.jingdong.service.impl;

import com.example.jingdong.pojo.ShopCategory;
import com.example.jingdong.repository.ShopCategoryRepository;
import com.example.jingdong.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

    @Autowired
    private ShopCategoryRepository repository;

    @Override
    public ShopCategory save(ShopCategory shopCategory) {
        return repository.save(shopCategory);
    }

    @Override
    public ShopCategory findOne(Integer id) {
        return repository.getReferenceById(id);
    }

    @Override
    public List<ShopCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ShopCategory> findAllByState(Integer state) {
        return repository.findAllByState(state);
    }
}
