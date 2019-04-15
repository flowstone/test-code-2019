package me.xueyao.service.impl;

import me.xueyao.service.AnimalService;
import org.springframework.stereotype.Service;

/**
 * @author: Simon.Xue
 * @date: 2019/4/15 10:22
 */
@Service("fishService")
public class FishServiceImpl implements AnimalService {
    @Override
    public void say() {
        System.out.println("咕咕叫");
    }
}
