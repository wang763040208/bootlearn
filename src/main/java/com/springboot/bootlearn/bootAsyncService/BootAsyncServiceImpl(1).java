package com.springboot.bootlearn.bootAsyncService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springboot.bootlearn.bootRedis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BootAsyncServiceImpl implements BootAsyncService{

    @Autowired
    private RedisUtil redisUtil;

    private static final String path = "C:\\Users\\sunz\\Desktop\\报销.txt";

    @Override
    @Async
    public void putBigDataForRedis() {
        long currentTimeMillis = System.currentTimeMillis();
        // 在E盘下新建一个文本文件
        File file1 = new File(path); // 创建File类对象
        FileInputStream fis = null; // 创建FileInputStream类对象读取File
        InputStreamReader isr = null; // 创建InputStreamReader对象接收文件流
        BufferedReader br = null; // 创建reader缓冲区将文件流装进去
        try {
            fis = new FileInputStream(file1);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            String lineTxt = null;
            // 从缓冲区中逐行读取代码，调用readLine()方法
            while ((lineTxt = br.readLine()) != null) {
                Map<String, String> bigDataMap = (Map)JSONObject.parse(lineTxt);
                redisUtil.set(bigDataMap.get("orderId"), bigDataMap.toString());// 逐行将数据插入的Redis中并设置缓存失效时间
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 文件执行完毕别忘了关闭数据流
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            long currentTimeMillis1 = System.currentTimeMillis();
            System.out.println("任务耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms");
        }
    }
}
