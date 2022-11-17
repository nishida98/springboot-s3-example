package com.example.demo;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.s3control.model.CreateBucketConfiguration;
import com.amazonaws.services.s3control.model.CreateBucketRequest;
import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

public class CreateBucket {

    private final AmazonS3 s3;

    public CreateBucket(AmazonS3 s3) {
        this.s3 = s3;
    }

    public void createBucket(String bucketName) {

        CreateBucketConfiguration createBucketConfiguration = new CreateBucketConfiguration();
        createBucketConfiguration.setLocationConstraint(Region.SA_SaoPaulo.getFirstRegionId());

        CreateBucketRequest createBucketRequest = new CreateBucketRequest();
        createBucketRequest.setBucket(bucketName);
        createBucketRequest.setCreateBucketConfiguration(createBucketConfiguration);

        if(!s3.doesBucketExistV2(bucketName))
            s3.createBucket(bucketName);
    }

    public void createFolder(String folder, String bucketName) {
        // Create metadata with content 0
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(0L);
        // Empty content
        InputStream inputStream = new ByteArrayInputStream(new byte[0]);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
                folder.toString() + "/", inputStream, metadata);
        s3.putObject(putObjectRequest);
    }

    public void deleteBucket(String bucketName) {
        s3.deleteBucket(bucketName);
    }

    public List list() {

        return s3.listBuckets();

    }

    public void upload(String bucketName) {

//        PutObjectRequest putObjectRequest =  new PutObjectRequest(bucketName, "teste/upload1.xml", new File("C:\\Users\\Leonardo.Nishida\\Documents\\Portal Diploma\\SafeWeb\\DiplomaTesteSafeWebv4.xml"));

        s3.putObject(bucketName, "teste/upload1.xml", new File("C:\\Users\\Leonardo.Nishida\\Documents\\Portal Diploma\\SafeWeb\\DiplomaTesteSafeWebv4.xml"));

    }

    public void uploadFile(String bucketName, MultipartFile file, String folder) throws IOException {

//        PutObjectRequest putObjectRequest =  new PutObjectRequest(bucketName, "teste/upload1.xml", new File("C:\\Users\\Leonardo.Nishida\\Documents\\Portal Diploma\\SafeWeb\\DiplomaTesteSafeWebv4.xml"));

        s3.putObject(bucketName, folder + "/" + file.getName(), convertMultiPartToFile(file));

    }

    public void download(String bucketname, String objectname) throws IOException {

        S3Object s3object = s3.getObject(bucketname, objectname);
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        FileUtils.copyInputStreamToFile(inputStream, new File("C:\\Users\\Leonardo.Nishida\\Documents\\Portal Diploma\\SafeWeb\\testeDownload.xml"));

    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
