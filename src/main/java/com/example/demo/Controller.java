package com.example.demo;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class Controller {

    private  ApplicationContext ctx;

    @GetMapping("/create-bucket")
    public void createBucket(@RequestParam("bucketname") String bucketname) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(S3PocApplication.class);
        AmazonS3 s3 = ctx.getBean(AmazonS3.class);

        CreateBucket createBucket = new CreateBucket(s3);
        createBucket.createBucket(bucketname);

    }

    @GetMapping("/create-folder")
    public void createFolder(@RequestParam("bucketname") String bucketname,@RequestParam("folder") String foldername) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(S3PocApplication.class);
        AmazonS3 s3 = ctx.getBean(AmazonS3.class);

        CreateBucket createBucket = new CreateBucket(s3);
        createBucket.createFolder(foldername, bucketname);

    }

    @GetMapping("/delete-bucket")
    public void deleteBucket(@RequestParam("bucketname") String bucketname) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(S3PocApplication.class);
        AmazonS3 s3 = ctx.getBean(AmazonS3.class);

        CreateBucket createBucket = new CreateBucket(s3);
        createBucket.deleteBucket(bucketname);

    }

    @GetMapping("/list-bucket")
    public List listBucket() {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(S3PocApplication.class);
        AmazonS3 s3 = ctx.getBean(AmazonS3.class);

        CreateBucket createBucket = new CreateBucket(s3);
        return createBucket.list();

    }

    @GetMapping("/upload")
    public void upload(@RequestParam("bucketname") String bucketname) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(S3PocApplication.class);
        AmazonS3 s3 = ctx.getBean(AmazonS3.class);

        CreateBucket createBucket = new CreateBucket(s3);
        createBucket.upload(bucketname);

    }

    @GetMapping("/download")
    public void download(@RequestParam("bucketname") String bucketname, @RequestParam("objectname") String objectname) throws IOException {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(S3PocApplication.class);
        AmazonS3 s3 = ctx.getBean(AmazonS3.class);

        CreateBucket createBucket = new CreateBucket(s3);
        createBucket.download(bucketname, objectname);

    }

    @PostMapping("/upload-file")
    public void uploadFile(@RequestParam("file")MultipartFile file) throws IOException {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(S3PocApplication.class);
        AmazonS3 s3 = ctx.getBean(AmazonS3.class);

        CreateBucket createBucket = new CreateBucket(s3);
        createBucket.uploadFile("teste-build-develop-bucket", file, "leo1");

    }

}
