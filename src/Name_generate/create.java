package Name_generate;

import java.io.InputStream;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;


@ManagedBean
@RequestScoped
public class create {
	
	
	
	
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public String path_create() {
		if(name.trim().length()>0) {
		Create_BucketS3(name);
		message("bucket Created");
		}
		else
			message("Enter a name of  bucket");
		return null;
	}
	
	
	

	public String path_list() {
		List_Bucket();
		return null;
	}
	
	
	
	private void message(String string) {
		FacesContext  context =FacesContext.getCurrentInstance();
		FacesMessage message=new FacesMessage(FacesMessage.SEVERITY_INFO,"",string);
		context.addMessage(null, message);
}
	
	
	
	
	AmazonS3 s3 = null;
	public void Create_BucketS3(String name2) {
	
		AWSCredentials   credentials =new
				BasicAWSCredentials(Credens.ACCESS_KEY_ID, Credens.ACCESS_SEC_KEY);
		    s3= AmazonS3ClientBuilder
			.standard()
			.withCredentials(new AWSStaticCredentialsProvider(credentials))
			.withRegion(Regions.EU_WEST_3)
			.build();
			    
			  if(!s3.doesBucketExistV2(name2)) {
			  try {
				    s3.createBucket(name2);
			  }catch (Exception e) {
				    System.out.println(e);
			}
			  
			  }
		}



	public void List_Bucket() {

		
		 List<Bucket> bucket =s3.listBuckets();
		 
		 for(Bucket  b : bucket) {
			 
			 System.out.println(b);
		 }
	}



}
