package wsrest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.EnumSet;

import database.DatabaseManagement;
import metier.Approval;
import metier.FormatedResponse;
import metier.ManualResponse;

@Path("/appManager")
public class ServiceAppManager {

	@POST
	@Path("/addApproval")
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addApproval(	@FormParam("idAccount") String id, 
									@FormParam("name") String name,
									@FormParam("response") String manualResponse) {
		FormatedResponse rep = new FormatedResponse();
		int status = 200;
		String message ="Request correctly handled";
		Boolean done = true;
		if((id != null) || (name != null) || (manualResponse != null)) {
			boolean doRequest = true;
			try {
				ManualResponse response = ManualResponse.valueOf(manualResponse);
			}catch(IllegalArgumentException e) {
				message = "Error with the response given";
				done = false;
				status = 400;
				doRequest = false;
			}
			if(doRequest) {
				Approval approval = new Approval(id,name,ManualResponse.valueOf(manualResponse));
				try {
					DatabaseManagement db = new DatabaseManagement();
					db.insertData(approval);
					rep.setData(approval);
				}catch (Exception e) {
					message = "Error with the database";
					done = false;
					status = 400;
				}
			}
		}else {
			message = "Error with the parameters given";
			done = false;
			status = 400;
		}
		rep.setMessage(message);
		rep.setDone(done);
		return Response.status(status).entity(rep).build();
	}
	
	@DELETE
	@Path("/removeApproval/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeApproval(@PathParam("id") String id) {
		FormatedResponse rep = new FormatedResponse();
		int status = 200;
		String message ="Request correctly handled";
		Boolean done = true;
		if((id != null)) {
			try{
				DatabaseManagement db = new DatabaseManagement();
				db.deleteData(id);
			}catch (Exception e){
				message = "Error with the database";
				done = false;
				status = 400;
			}
		}else{
			message = "Error with the parameter given";
			done = false;
			status = 400;
		}
		rep.setMessage(message);
		rep.setDone(done);
		return Response.status(status).entity(rep).build();
	}
	
	@GET
	@Path("/listApproval")
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listApproval() {
		FormatedResponse rep = new FormatedResponse();
		int status = 200;
		String message ="Request correctly handled";
		Boolean done = true;
		List<Approval> result;
		try{
			DatabaseManagement db = new DatabaseManagement();
			result = db.selectDatas();
			rep.setData(result);
		}catch (Exception e){
			message = "Error with the database";
			done = false;
			status = 400;
		}
		rep.setMessage(message);
		rep.setDone(done);
		return Response.status(status).entity(rep).build();
	}
	
	@GET
	@Path("/getApproval/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getApproval(@PathParam("id") String id) {
		FormatedResponse rep = new FormatedResponse();
		int status = 200;
		String message ="Request correctly handled";
		Boolean done = true;
		Approval result;
		try{
			DatabaseManagement db = new DatabaseManagement();
			result = db.selectData(id);
			if(result == null) {
				message = "Error, the approval is unobtainable";
				done = false;
				status = 400;
			}else{
				rep.setData(result);
			}
		}catch (Exception e){
			message = "Error with the database";
			done = false;
			status = 400;
		}
		rep.setMessage(message);
		rep.setDone(done);
		return Response.status(status).entity(rep).build();
	}
}
