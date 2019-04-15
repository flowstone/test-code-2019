package me.xueyao.service.impl;

import me.xueyao.service.AnimalService;
import org.springframework.stereotype.Service;

/**
 *
 * @author: Simon.Xue
 * @date: 2019/4/15 10:21
 */
@Service("dogService")
public class DogServiceImpl implements AnimalService {
    @Override
    public void say() {
        System.out.println("汪汪叫");
    }
}
