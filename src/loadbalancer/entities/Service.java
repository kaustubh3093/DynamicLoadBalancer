package loadbalancer.entities;

public class Service {
	// Service URL.
	private String URL;
	// Service name.
	private String name;
    public Service(){
    	
    }
    public Service(String nameValue , String urlPath){
    	name = nameValue;
    	URL = urlPath;
    }
    
    //Getter method for the Service class
    public String getName() {
    	return name;
    }
    
    public String getUrl() {
    	return URL;
    }
  //Setter method for the Service class
    public void setname(String name_) {
    	name = name_;
    }
    public void setUrl(String Url_) {
    	URL = Url_;
    }
}
