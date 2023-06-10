package com.boluo.groundedsegmentanythingbackend.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author kirito
 * @version 1.0
 * @description: TODO 主要用于创建进程与python通信
 * @date 2023/6/9 20:04
 */
@Slf4j
public class ComUtils {

    public static Boolean comWithPython(String evn,String workPath,String ...args){

        // 传入参数
        List<String> arguments = new ArrayList<>();
        arguments.add(evn);
        arguments.add(workPath);
        arguments.addAll(Arrays.asList(args));
        // 接收参数
        List<String> res = new ArrayList<>();

        try {
            ProcessBuilder processBuilder =new ProcessBuilder(arguments);
            Process proc = processBuilder.start();


            String error = null;
            BufferedReader err = new BufferedReader(new InputStreamReader((proc.getErrorStream()),"gbk"));
            while((error = err.readLine()) != null) {

            }

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(),"gbk"));
            String read = null;

            while((read = in.readLine()) != null) {
                res.add(read);
            }

            log.info(res.toString());
            int wait = proc.waitFor();
            System.out.println("wait = " + wait);
            err.close();
            in.close();
            proc.destroy();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
