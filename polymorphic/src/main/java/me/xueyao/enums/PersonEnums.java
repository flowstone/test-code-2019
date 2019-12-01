package me.xueyao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.xueyao.service.impl.HeadMaster;
import me.xueyao.service.impl.Student;
import me.xueyao.service.impl.Teacher;

/**
 * @author Simon.Xue
 * @date 2019-12-01 15:55
 **/
@AllArgsConstructor
@Getter
public enum  PersonEnums {
    STUDENT(1, "学生", Student.class),
    TEACHER(2, "老师", Teacher.class),
    HEADMASTER(3, "校长", HeadMaster.class);

    Integer code;
    String msg;
    Class clazz;

    /**
     * 获得类的名称，因为Spring自动注入时，默认名称是类名(首字母小写)
     * @param code
     * @return
     */
    public static String className(Integer code) {
        for (PersonEnums value : values()) {
            if (value.getCode().equals(code)) {
                String simpleName = value.getClazz().getSimpleName();
                simpleName.substring(1);
                return String.valueOf(simpleName.charAt(0)).toLowerCase() + simpleName.substring(1);
            }
        }
        return "";
    }

}
