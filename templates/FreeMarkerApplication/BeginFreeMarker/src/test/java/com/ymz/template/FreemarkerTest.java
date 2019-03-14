package com.ymz.template;

import java.io.StringWriter;
import java.util.*;

import com.ymz.template.pojo.Group;
import com.ymz.template.pojo.User;
import com.ymz.template.util.FreemarkerUtil;
import org.junit.Test;

public class FreemarkerTest {


    //基础输出路径
    private static String BASE_PATH = "D:\\depmt\\workspaces\\javas\\owns\\projects\\templates\\FreeMarkerApplication\\BeginFreeMarker\\src\\main\\resources\\template\\";


    /**
     * 输出到控制台
     */
    @Test
    public void testConsole() {
        //1.创建数据模型
        Map<String, Object> root = new HashMap<String, Object>();
        //2.赋值
        root.put("username", "胖先生");
        //3.将数据模型和模版进行结合输出到控制台显示
        FreemarkerUtil.print("01.ftl", root);
        System.out.println("\n");
        System.out.println("==ymz 处理成功！");
    }

    /**
     * 输出到制定输入流
     */
    @Test
    public void testWriter() {
        //1.创建数据模型
        Map<String, Object> root = new HashMap<String, Object>();
        //2.赋值
        root.put("username", "胖先生");
        //3.将数据模型和模版进行结合输出到控制台显示
        StringWriter sw = new StringWriter();
        FreemarkerUtil.writePrint("01.ftl", root, sw);
        System.out.println(sw.toString());
        System.out.println("==ymz 处理成功！");
    }

    /**
     * 文件输出测试：使用Map作为数据
     */
    @Test
    public void test01() {
        //在ftl中要赋值的变量
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("username", "王风");
        FreemarkerUtil.filePrint("01.ftl", root, BASE_PATH + "01.html");
        System.out.println("==ymz 输出成功！");
    }

    /**
     * 文件输出测试：使用对象作为数据源
     */
    @Test
    public void test02() {
        //在ftl中要赋值的变量
        Map<String, Object> root = new HashMap<String, Object>();
        User user = new User();
        user.setId(1011);
        user.setName("汪峰");
        user.setAge(52);
        root.put("user", user);
        FreemarkerUtil.filePrint("02.ftl", root, BASE_PATH + "02.html");
        System.out.println("==ymz 输出成功！");
    }

    /**
     * 文件输出测试：使用列表作为数据源
     */
    @Test
    public void test03() {
        Map<String, Object> root = new HashMap<String, Object>();
        User user = new User();
        user.setId(1011);
        user.setName("汪峰");
        user.setAge(52);
        User user1 = new User();
        user1.setId(1012);
        user1.setName("章子怡");
        user1.setAge(15);
        List<User> users = new ArrayList<User>();
        users.add(user);
        users.add(user1);
        root.put("users", users);//在ftl中要赋值的变量
        FreemarkerUtil.filePrint("03.ftl", root, BASE_PATH + "03.html");
        System.out.println("==ymz 输出成功！");
    }


    /**
     * 文件输出测试：使用属性的属性为数据源
     */
    @Test
    public void test04() {
        Map<String, Object> root = new HashMap<String, Object>();
        User user = new User();
        user.setId(1011);
        user.setName("汪峰");
        user.setAge(52);
        Group group = new Group();
        group.setName("雅昌");
        user.setGroup(group);
        root.put("user", user);//在ftl中要赋值的变量
        root.put("book", "美丽说");
        FreemarkerUtil.filePrint("04.ftl", root, BASE_PATH + "04.html");
        System.out.println("==ymz 输出成功！");
    }

    /**
     * 文件输出测试：格式输出
     */
    @Test
    public void test05() {
        //在ftl中要赋值的变量
        Map<String, Object> root = new HashMap<String, Object>();

        //对象格式验证
        User user = new User();
        user.setId(1011);
        user.setName("汪峰");
        user.setAge(52);
        Group group = new Group();
        group.setName("雅昌");
        user.setGroup(group);
        root.put("user", user);

        //日期格式验证
        Date date = new Date();
        root.put("book", "a美丽说");
        root.put("date", date);

        //数据格式验证
        int number = 21;
        root.put("number", number);
        Boolean foo = true;
        root.put("foo", foo);

        FreemarkerUtil.filePrint("05.ftl", root, BASE_PATH + "05.html");
        System.out.println("==ymz 输出成功！");
    }


}
