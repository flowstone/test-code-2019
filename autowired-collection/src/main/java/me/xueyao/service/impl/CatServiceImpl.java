package me.xueyao.service.impl;

import me.xueyao.service.AnimalService;
import org.springframework.stereotype.Service;

/**
 * @author: Simon.Xue
 * @date: 2019/4/15 10:19
 */
@Service("catService")
public class CatServiceImpl implements AnimalService {
    @Override
    public void say() {
        System.out.println("喵喵叫");
    }
}
