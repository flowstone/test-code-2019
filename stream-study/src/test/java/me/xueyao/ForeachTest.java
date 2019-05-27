package me.xueyao;

import me.xueyao.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Simon.Xue
 * @date: 2019/5/27 9:44
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StreamStudyApplication.class)
public class ForeachTest {


    @Test
    public void foreachTest() {

        List<User> users = new ArrayList<>();

        User user = new User();
        user.setName("小明");
        user.setEmail("");
        user.setAge(18);
        users.add(user);

        //users.forEach(use->use.setEmail("xiaoming@gmail.com"));
        users.forEach(use->{
            if (use.getAge() == 1) {
                use.setEmail("xiaoming@gmail.com");
            } else {
                use.setEmail("xueyao.me@gmail.com");
            }
        });


        List<Integer> collect = users.stream().map(a -> a.getAge()).collect(Collectors.toList());

        users.forEach(user1->System.out.print(user1.getEmail()));

        collect.forEach(c->System.out.print(c));
    }
}
