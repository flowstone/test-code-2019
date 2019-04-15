package me.xueyao;

import me.xueyao.service.AnimalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AutowiredCollectionApplication.class)
public class AutowiredCollectionApplicationTests {

    @Autowired
    private Map<String, AnimalService> animalServiceMap;

    @Autowired
    private List<AnimalService> animalServiceList;
    @Test
    public void contextLoads() {
    }

    @Test
    public void testAnimalMapSay() {
        for (Map.Entry<String, AnimalService> stringAnimalServiceEntry : animalServiceMap.entrySet()) {
            System.out.println(stringAnimalServiceEntry.getKey() + "-------" + stringAnimalServiceEntry.getValue());
            stringAnimalServiceEntry.getValue().say();
        }
    }

    @Test
    public void testAnimalListSay() {
        for (AnimalService animalService : animalServiceList) {
            animalService.say();
        }
    }



}
