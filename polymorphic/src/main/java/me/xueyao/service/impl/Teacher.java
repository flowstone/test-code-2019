package me.xueyao.service.impl;

import me.xueyao.service.Person;
import org.springframework.stereotype.Service;

/**
 * @author Simon.Xue
 * @date 2019-12-01 14:37
 **/
@Service
public class Teacher implements Person {
    @Override
    public void say() {
        System.out.println("老师应该这样说");
    }
}
