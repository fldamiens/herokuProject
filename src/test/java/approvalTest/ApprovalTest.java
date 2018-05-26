package approvalTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import metier.Approval;
import metier.FormatedResponse;
import wsrest.ServiceAppManager;

public class ApprovalTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(ServiceAppManager.class);
    }

    @Test
    public void testInsert() {
    	Client client = ClientBuilder.newClient();
    	WebTarget target = client.target("https://rocky-sierra-35610.herokuapp.com").path("/appManager/addApproval");
    	 
    	Form data = new Form();
    	data.param("idAccount", "342e4b0f-fc38-47a4-9dd5-caccc4296e76");
    	data.param("name", "jhj");
    	data.param("response", "ACCEPTED");
    	 
    	FormatedResponse response =
    	target.request(MediaType.APPLICATION_JSON_TYPE)
    	    .post(Entity.entity(data,MediaType.APPLICATION_FORM_URLENCODED_TYPE),
    	    		FormatedResponse.class);
    
        assertEquals("Request correctly handled", response.getMessage());
        assertTrue(response.getDone());
    }
    
    @Test
    public void testDelete() {
    	Client client = ClientBuilder.newClient();
    	WebTarget target = client.target("https://rocky-sierra-35610.herokuapp.com").path("/appManager/addApproval");
    	 
    	Form data = new Form();
    	data.param("idAccount", "342e4b0f-fc38-47a4-9dd5-caccc4296e76");
    	data.param("name", "jhj");
    	data.param("response", "ACCEPTED");
    	 
    	FormatedResponse response =
    	target.request(MediaType.APPLICATION_JSON_TYPE)
    	    .post(Entity.entity(data,MediaType.APPLICATION_FORM_URLENCODED_TYPE),
    	    		FormatedResponse.class);
    
        assertEquals("Request correctly handled", response.getMessage());
        assertTrue(response.getDone());
        
        WebTarget remove = client.target("https://rocky-sierra-35610.herokuapp.com").path("/appManager/removeApproval/").path("1");
    	
    	FormatedResponse responseRemove =
    	remove.request(MediaType.APPLICATION_JSON_TYPE)
    	    .delete(FormatedResponse.class);
    
        assertEquals("Request correctly handled", responseRemove.getMessage());
    }
    
    @Test
    public void testSelectAll() {
    	List<Approval> list;
     	Client client = ClientBuilder.newClient();
    	WebTarget target = client.target("https://rocky-sierra-35610.herokuapp.com").path("/appManager/addApproval");
    	 
    	Form data = new Form();
    	data.param("idAccount", "342e4b0f-fc38-47a4-9dd5-caccc4296e76");
    	data.param("name", "jhj");
    	data.param("response", "ACCEPTED");
    	 
    	FormatedResponse response =
    	target.request(MediaType.APPLICATION_JSON_TYPE)
    	    .post(Entity.entity(data,MediaType.APPLICATION_FORM_URLENCODED_TYPE),
    	    		FormatedResponse.class);
    
        assertEquals("Request correctly handled", response.getMessage());
        assertTrue(response.getDone());
        
        WebTarget res = client.target("https://rocky-sierra-35610.herokuapp.com").path("/appManager/listApproval");
    	
    	FormatedResponse responseList =
    	res.request(MediaType.APPLICATION_JSON_TYPE)
    	    .get(FormatedResponse.class);
   
        assertEquals("Request correctly handled", responseList.getMessage());
    }
}
