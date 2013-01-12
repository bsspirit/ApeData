package org.ape.data.core.util;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringInitialize {

    private static ApplicationContext ctx = null;

    private SpringInitialize() {
    }

    public static ApplicationContext getContext() {
        if (ctx == null) {
            ctx = new ClassPathXmlApplicationContext("spring.xml");
        }
        return ctx;
    }

    public static void main(String[] args) throws Exception {
        // hdfs();
        // database();

        mapreduce();
    }

    static void mapreduce() throws Exception {
//        JobLauncher jobLauncher = SpringInitialize.getContext().getBean(JobLauncher.class);
//        Job job = SpringInitialize.getContext().getBean(Job.class);
//
//        Map<String, JobParameter> map = new HashMap<String, JobParameter>();
//        map.put("word.input", new JobParameter("/user/conan/word/input/"));
//        map.put("word.output", new JobParameter("/user/conan/word/output/"));
//        jobLauncher.run(job, new JobParameters(map));

    }

    static void hdfs() throws IOException {
//        HdfsService hdfs = (HdfsService) SpringInitialize.getContext().getBean("hdfsService");
//        hdfs.rmr("/user/conan/word");
//        hdfs.mkdirs("/user/conan/word/input");
//        // hdfs.mkdirs("/user/conan/word/output");
//        hdfs.copyFile("data/nietzsche-chapter-1.txt", "/user/conan/word/input");
//        hdfs.ls("/user/conan/word/input");
//        hdfs.download("/user/conan/word/", "data/word");
    }

    static void database() {
//        	
    }

}