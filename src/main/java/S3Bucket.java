import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;

public class S3Bucket {
    public static void main(String[] args) {
        S3Client s3Client = S3Client.builder().build();
        CreateBucketRequest createBucketRequest = CreateBucketRequest.builder()
                .bucket("zhaozhiwei")
                .build();
        try{
            s3Client.createBucket(createBucketRequest);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}
