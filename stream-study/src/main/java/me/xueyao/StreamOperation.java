package me.xueyao;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 流的操作
 * @author: Simon.Xue
 * @date: 2019/5/10 15:46
 */
public class StreamOperation {
    public static void main(String[] args) throws IOException {

        String contents = "This  is a book! You should go to school!";
        List<String> list = Arrays.asList(contents.split(" "));
        long count = 0;
        for (String w : list) {
            if (w.length() > 5) {
                count++;
            }
        }

        list.stream().filter(w -> w.length() > 12).count();

        list.parallelStream().filter(w -> w.length() > 12).count();

        Stream<List<String>> list1 = Stream.of(list);
        Stream<String> song = Stream.of("gently", "down", "the", "stream");
        Stream<String> stringStream = list.stream().map(String::toLowerCase);
        //list.stream().map()
        System.out.println(count);

    }
}
