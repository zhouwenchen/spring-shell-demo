package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.Locale;

@ShellComponent
public class TranslationCommands {

    @ShellMethod("Say hello")
    public void hello(String name) {
        System.out.println("hello, " + name + "!");
    }


    // 为一个命令指定多个名称
    @ShellMethod(value = "Add numbers.", key = {"sum", "addition"})
    public void add(int a, int b) {
        int sum = a + b;
        System.out.println(String.format("%d + %d = %d", a, b, sum));
    }

    // 1.使用属性value定义命令描述
// 2.使用属性key定义命令名称
// 3.使用属性prefix定义参数前缀
// 4.使用属性group定义命令分组

    /**
     * addition1 -a 3 -b 3
     * sum1 -a 1 -b 2
     * @param a
     * @param b
     */
    @ShellMethod(value = "Add numbers.", key = {"sum1", "addition1"}, prefix = "-", group = "Cal")
    public void add1(int a, int b) {
        int sum = a + b;
        System.out.println(String.format("%d + %d = %d", a, b, sum));
    }

    /**
     * ShellOption 可以自定义参数名称
     * echo --a 1 --b 2 --third 3
     *
     * @param a
     * @param b
     * @param c
     */
    @ShellMethod("Echo params")
    public void echo(int a, int b, @ShellOption("--third") int c) {
        System.out.println(String.format("a=%d, b=%d, c=%d", a, b, c));
    }

    /**
     * 使用注解@ShellOption还可以为命令参数指定多个名称：
     *
     * @param cmd
     */
    @ShellMethod("Echo command help")
    public void myhelp(@ShellOption({"-C", "--command"}) String cmd) {
        System.out.println(cmd);
    }

    /**
     * 设置参数默认值
     * @param name
     */
    @ShellMethod("Say hello")
    public void defautevalue(@ShellOption(defaultValue = "World") String name) {
        System.out.println("hello, " + name + "!");
    }

    // 参数为一个数组

    /**
     * add-by-array 3 1 2
     * @param numbers
     */
    @ShellMethod("Add by array")
    public void addByArray(@ShellOption(arity = 3) int[] numbers) {
        int sum = 0;
        for(int number : numbers) {
            sum += number;
        }
        System.out.println(String.format("sum=%d", sum));
    }

    /**
     * 参数为集合
     * add-byd-list 3 3 3
     */
    @ShellMethod("Add by list")
    public void addByList(@ShellOption(arity = 3) List<Integer> numbers) {
        int s = 0;
        for(int number : numbers) {
            s += number;
        }
        System.out.println(String.format("s=%d", s));
    }


    /**
     * 参数为Boolean类型
     * shutdown
     * shutdown=false
     *
     * shell:>shutdown --shutdown
     * shutdown=true
     *
     * 从上述示例可以知道，对于布尔类型的参数，默认值为false，当明确传递参数名时，值为true
     */
    @ShellMethod("Shutdown action")
    public void shutdown(boolean shutdown) {
        System.out.println(String.format("shutdown=%s", shutdown));
    }

    /**
     *  带空格的参数需要使用引号引起来
     *  withblack "a b c "
     */
    @ShellMethod("Echo.")
    public void withblack(String what) {
        System.out.println(what);
    }

    /**
     * 使用@Size注解校验参数长度
     */
    @ShellMethod("Change password")
    public void changePwd(@Size(min = 6, max = 30) String pwd) {
        System.out.println(pwd);
    }

    /**
     * 动态命令可用性
     */
}