package me.xueyao.service.impl;

import me.xueyao.service.Person;
import org.springframework.stereotype.Service;

/**
 * @author Simon.Xue
 * @date 2019-12-01 14:34
 **/
@Service
public class Student implements Person {
    @Override
    public void say() {
        System.out.println("学生应该这样说话");
    }
}
