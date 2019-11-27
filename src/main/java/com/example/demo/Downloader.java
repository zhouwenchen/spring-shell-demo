package com.example.demo;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * 显然，在这种方式下，必须为需要实现动态可用性的命令提供一个对应名称的方法（方法名必须是：“命令名 + Availability”，如：downloadAvailability），且方法的返回值必须为org.springframework.shell.Availability对象。
 *  该方式的缺点也很明显，如果需要实现动态可用性的命令比较多，必须定义同等数量的可用性方法，比较繁琐。
 *
 *  shell:>download
 * Command 'download' exists but is not currently available because you are not connected
 * Details of the error have been omitted. You can use the stacktrace command to print the full stacktrace.
 * shell:>connect
 * shell:>download
 * Downloaded.
 * shell:>
 *
 */
@ShellComponent
public class Downloader {
    private boolean connected = false;

    @ShellMethod("Connect server")
    public void connect() {
        connected = true;
    }

    @ShellMethod("Download file")
    public void download() {
        System.out.println("Downloaded.");
    }

    // 为命令download提供可用行支持
    public Availability downloadAvailability() {
        return connected ? Availability.available():Availability.unavailable("you are not connected");
    }
}