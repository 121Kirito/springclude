package com.itheima.mp.domain.po;

import java.util.Arrays;
import java.util.Scanner;

public class StudentManagementSystem {
    private static String[] usernames = new String[0]; // 用于存储用户账号
    private static String[] passwords = new String[0]; // 用于存储用户密码

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("===============欢迎使用学生管理系统===============");
            System.out.println("学生管理系统");
            System.out.println("1. 注册");
            System.out.println("2. 登录");
            System.out.println("3. 查看所有用户");
            System.out.println("4. 退出");
            System.out.print("请选择操作：");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 清除换行符

            switch (choice) {
                case 1:
                    register(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    viewUsers();
                    break;
                case 4:
                    System.out.println("系统已退出。");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("无效选择，请重试。");
            }
        }
    }

    // 注册功能
    private static void register(Scanner scanner) {
        System.out.print("请输入新用户名：");
        String username = scanner.nextLine();
        System.out.print("请输入新密码：");
        String password = scanner.nextLine();

        // 扩展数组以存储新用户信息
        usernames = Arrays.copyOf(usernames, usernames.length + 1);
        passwords = Arrays.copyOf(passwords, passwords.length + 1);
        usernames[usernames.length - 1] = username;
        passwords[passwords.length - 1] = password;

        System.out.println("注册成功！");
    }

    // 登录功能
    private static void login(Scanner scanner) {
        System.out.print("请输入用户名：");
        String username = scanner.nextLine();
        System.out.print("请输入密码：");
        String password = scanner.nextLine();

        // 检查用户名和密码
        boolean loginSuccess = false;
        for (int i = 0; i < usernames.length; i++) {
            if (usernames[i].equals(username) && passwords[i].equals(password)) {
                loginSuccess = true;
                break;
            }
        }

        if (loginSuccess) {
            System.out.println("登录成功！");
        } else {
            System.out.println("登录失败，用户名或密码错误。");
        }
    }

    // 查看所有用户功能
    private static void viewUsers() {
        if (usernames.length == 0) {
            System.out.println("当前没有用户注册。");
            return;
        }
        System.out.println("已注册的用户：");
        for (int i = 0; i < usernames.length; i++) {
            System.out.println("用户 " + (i + 1) + "：用户名 = " + usernames[i] + "，密码 = " + passwords[i]);
        }
    }
}
