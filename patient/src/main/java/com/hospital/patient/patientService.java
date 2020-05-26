package com.hospital.patient;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Root resource (exposed at "patientService" path)
 */
@Path("myresource")
public class patientService {
	
	
	patient pObj = new patient();

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Path("/read") 
    @Produces(MediaType.APPLICATION_JSON)
    public String getIt() {
        //return "Got it!";
    	return pObj.readPatient();
    }
    
    
    @POST 
    @Path("/insert") 
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
    @Produces(MediaType.TEXT_PLAIN) 
    public String insertItem(@FormParam("pname") String pname,       
    						@FormParam("gender") String gender,    
    						@FormParam("address") String address,       
    						@FormParam("phone") String phone) 
    {  
    	String output = pObj.insertPatient(pname, gender, address, phone);  
    	return output; 
    } 
    
    
    
    @PUT 
    @Path("/update") 
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.TEXT_PLAIN) 
    public String updateItem(String itemData) {  

    	//Convert the input string to a JSON object  
    	JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
     
    	//Read the values from the JSON object 
    	int pid = itemObject.get("pid").getAsInt();  
    	String pname = itemObject.get("pname").getAsString();  
    	String gender = itemObject.get("gender").getAsString();  
    	String address = itemObject.get("address").getAsString();  
    	int phone = itemObject.get("phone").getAsInt();  
    
     
        String output = pObj.updatePatient(pid,pname, gender, address, phone); 
     
    	return output; 
    } 
    
    
    
    @DELETE 
    @Path("/delete") 
    @Consumes(MediaType.APPLICATION_XML) 
    @Produces(MediaType.TEXT_PLAIN) 
    public String deleteItem(String itemData) {  
    	
    	//Convert the input string to an XML document  
    	Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());     
    	
    	//Read the value from the element <itemID>  
    	String pid = doc.select("pid").text(); 
     
    	String output = pObj.deletePatient(pid); 
     
    	return output; 
    	
    }
    
    
}
