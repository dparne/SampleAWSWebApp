package com.dparne.sample;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;

public class SimpleTest {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		
		Properties prop = new Properties();
		InputStream input = null;
	 
		try {
	 
			//Read credentials from file.
			input = new FileInputStream("/Users/dineshparne/Work/PersonalWorkspace/AWS/SampleWebApplication/src/AwsCredentials.properties");
			prop.load(input);
	 
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		AWSCredentials awsCredentials = new BasicAWSCredentials(
				prop.getProperty("accessKey"), prop.getProperty("secretKey"));
		AmazonS3 s3 = new AmazonS3Client(awsCredentials);


		for (Bucket bucket : s3.listBuckets()) {
			System.out.println(bucket.getName());
		}
	}
}
