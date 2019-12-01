package me.xueyao;

import me.xueyao.enums.PersonEnums;
import me.xueyao.service.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Simon.Xue
 * @date 2019-12-01 14:42
 **/
@SpringBootTest
public class PersonTest {

    @Autowired
    private Map<String, Person> personMap = new HashMap<>();
    @Test
    public void testSay() {

        personMap.get(PersonEnums.className(2)).say();
    }



    @Test
    public void testBaseSay() {
        //1 代表学生 2老师   3校长
        int type = 1;
        if (1 == type) {
            System.out.println("学生笑嘻嘻的说话");
        } else if (2 == type) {
            System.out.println("老师开心的说话");
        } else {
            System.out.println("校长严肃的说话");
        }
    }
}
