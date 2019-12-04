package me.xueyao;

import com.lowagie.text.rtf.style.RtfParagraphStyle;
import me.xueyao.utils.WordUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ItextWordApplicationTests {

    @Autowired
    private WordUtils wordUtils;
    @Test
    void contextLoads () throws Exception {
        wordUtils.openDocument("/Users/simonxue/Desktop/abc.doc");
        wordUtils.insertTitlePattern("小明", RtfParagraphStyle.STYLE_HEADING_1);
        wordUtils.insertTableName("表格", 15, 15, 1);
        wordUtils.insertSimpleTable(3, 1);
        wordUtils.closeDocument();
    }


}
