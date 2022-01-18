import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.textract.TextractClient;
import software.amazon.awssdk.services.textract.model.*;

import java.util.ArrayList;
import java.util.List;

public class Textract {
    public static void main(String[] args) {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().build();
        S3Object s3Object = s3Client.getObject("zhaozhiwei","Sample-Filled-PF-Form.jpg"); // get object from s3 bucket
        S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent(); // get as input stream
        SdkBytes sdkBytes = SdkBytes.fromInputStream(s3ObjectInputStream); // convert to byte
        Document document = Document.builder().bytes(sdkBytes).build(); // as textract doc

        List<FeatureType> list = new ArrayList<>();
        list.add(FeatureType.FORMS); // add the type of content in the doc to be extracted

        AnalyzeDocumentRequest analyzeDocumentRequest = AnalyzeDocumentRequest.builder().featureTypes(list).document(document).build();
        TextractClient textractClient = TextractClient.builder().region(Region.AP_SOUTH_1).build();

        // use analyse document api
        AnalyzeDocumentResponse analyzeDocumentResponse = textractClient.analyzeDocument(analyzeDocumentRequest);
        List<Block> blocks = analyzeDocumentResponse.blocks();

        for(Block b: blocks)
        {
            System.out.println(b.toString());
        }




    }
}
