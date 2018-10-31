package com.zhiyou100.doccloud.job;

import com.zhiyou100.doccloud.util.HdfsUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/*
*@ClassName:DocJobHandler
 @Description:TODO
 @Author:
 @Date:2018/10/30 17:09 
 @Version:v1.0
*/
@Slf4j
public class DocJobHandler implements Runnable {
    private DocJob docJob;

    public DocJobHandler(DocJob docJob) {
        this.docJob = docJob;
        log.info("start to deal job {}",docJob);
    }

    public void run() {
        //得到输入路径
        String input = docJob.getInput();
        String tmpWorkDirPath = "/tmp/docjobdaemon/" + UUID.randomUUID().toString() + "/";
        //创建临时工作目录
        File tmpWorkDir = new File(tmpWorkDirPath);
        tmpWorkDir.mkdirs();
        //下载文件到临时目录
        try {
            HdfsUtil.download(input, tmpWorkDirPath);
            log.info("download file to {}",tmpWorkDirPath);
            //step1：文档转换成html
            String command = "soffice --headless --invisible --convert-to html " + docJob.getFileName();
            Process process = Runtime.getRuntime().exec(command, null, tmpWorkDir);
            System.out.println(IOUtils.toString(process.getInputStream()));
            System.out.println(IOUtils.toString(process.getErrorStream()));
            //step2 转换成pdf
            //step3 提取页码
            //step4 提取首页缩略图
            //step5 利用solr建立索引
            //step6 上传结果
            //step7 清理临时目录
            //step8 任务成功回调
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
