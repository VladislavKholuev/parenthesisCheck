package com.company;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Main {
    
    public static Set<Character> file_con = new HashSet<>();

    public static String checking(String input_str){

        StringBuilder str_for_check = new StringBuilder(input_str);
        if (str_for_check.length() == 0){
            return "������ ������";
        }

        System.out.println(str_for_check + " ����");
        int lengt_to_delete = str_for_check.length();

        for(int i = 0; i <input_str.length() ;i++)
            if(file_con.contains(str_for_check.charAt(i))){
                str_for_check.append(str_for_check.charAt(i));
            }

        str_for_check.delete(0,lengt_to_delete);
        System.out.println(str_for_check);
        Stack<Character> stack = new Stack<>();
        stack.push(' ');
        String function_response = "������ �����������  ���������";
//        int res = 1;
        boolean check=true;
        boolean stat = true;
        for (int i = 0; i < str_for_check.length(); i++) {
            switch (str_for_check.charAt(i)) {
                case '(':
                case '<':
                case '[':
                case '{':
                    stack.push(str_for_check.charAt(i));
                    break;
                case ')':
                    if ((stack.peek() != '(') || stack.empty()) {
                        stat = false;
                        break;
                    } else stack.pop();
                    break;
                case ']':
                    if ((stack.peek() != '[') || stack.empty()) {
                        stat = false;
                        break;
                    } else stack.pop();
                    break;
                case '}':
                    if ((stack.peek() != '{') || stack.empty()) {
                        stat = false;
                        break;
                    } else stack.pop();
                    break;
                case '>':
                    if ((stack.peek() != '<') || stack.empty()) {
                        stat = false;
                        break;
                    } else stack.pop();
                    break;
                case '|':
                    if(check == true) {
                        stack.push(str_for_check.charAt(i));
                        check = false;
                    }
                    else if(check ==false &&((stack.peek() != '|') || stack.empty())){
                        stat = false;
//                        check = true;
                    }  else {
                        stack.pop();
                        check = true;
                    }

            }
            if (!stat) {
                function_response = "������ ����������� �� ���������, ������ � ������� " + i+ " "+str_for_check.charAt(i);
                break;
            }
        }
        stack.pop();
        if (!stack.empty()) function_response = "������ ����������� �� ���������";

        return function_response;
    }

    public static void main(String[] args) {

        BufferedReader reader = null;
        //������ ����� ������������ � ������������ ������ ������
        try {
            reader = new BufferedReader(new FileReader("file_config.txt"));

            int symbol;

            while((symbol = reader.read()) != -1) {

                if(symbol =='[' || symbol ==']' || symbol =='{' || symbol =='}' || symbol =='(' || symbol ==')'|| symbol =='|' ) {

                    file_con.add((char)symbol);

                    System.out.println((char)symbol+  "   " + file_con);
                }
            }
            System.out.println(file_con);
        }
        catch (IOException e) {
            System.out.println("mistake in file_config");
        }

        try {
            String content = Files.lines(Paths.get("file_input.txt")).reduce("", String::concat);

            System.out.println(checking(content));

        }
        catch (IOException e) {
            System.out.println("mistake in file_input");
        }

    }
}

//����������� ������ � ������� ����������� �������� <<< >>> << >>
//������� & - �������, �� ��������� ��� ��������, && - ��������� ������ �� �������
//

//�������� ��� ������� � ������