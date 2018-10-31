package com.zhiyou100.doccloud.doccloudweb.util;

import com.google.common.io.Resources;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.IOException;

/*
*@ClassName:HdfsUtil
 @Description:TODO
 @Author:
 @Date:2018/10/29 17:17 
 @Version:v1.0
*/
public class HdfsUtil {
    //文档上传工具类
    public static void upload(byte[] src, String docName, String dst) throws IOException {
        //加载配置文件
        Configuration coreSiteConf = new Configuration();
        coreSiteConf.addResource(Resources.getResource("core-site.xml"));
        //获取文件系统客户端对象
        FileSystem fileSystem = FileSystem.get(coreSiteConf);

        FSDataOutputStream fsDataOutputStream = fileSystem.create(new Path(dst + "/" + docName));

        fsDataOutputStream.write(src);
        fsDataOutputStream.close();
        fileSystem.close();
    }

    public static void main(String[] args) throws IOException {
        getMetaData("hdfs://192.168.244.3:9000/doccloud//2018-10-29/60f7dff0-8447-4310-ad17-841a2b0670c2/hadoopclientcode.docx");
    }
    public static void getMetaData(String path) throws IOException {
        Configuration coreSiteConf = new Configuration();
        coreSiteConf.addResource(Resources.getResource("core-site.xml"));
        //获取文件系统客户端对象
        FileSystem fileSystem = FileSystem.get(coreSiteConf);
        FileStatus fileStatus = fileSystem.getFileStatus(new Path(path));
        BlockLocation[] fileBlockLocations = fileSystem.getFileBlockLocations(fileStatus, 0, fileStatus.getLen());
        //fileSystem.s
        fileSystem.getContentSummary(new Path(""));
        fileSystem.open(new Path("/"));
        fileSystem.listLocatedStatus(new Path("")).next();
        for (BlockLocation bl :
                fileBlockLocations) {


            System.out.println(bl);

        }

        System.out.println(fileBlockLocations);

    }
}
