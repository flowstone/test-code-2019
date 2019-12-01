package me.xueyao.service.impl;

import me.xueyao.service.Person;
import org.springframework.stereotype.Service;

/**
 * @author Simon.Xue
 * @date 2019-12-01 14:41
 **/
@Service
public class HeadMaster implements Person {

    @Override
    public void say() {
        System.out.println("我是校长，我喜欢说话");
    }
}
