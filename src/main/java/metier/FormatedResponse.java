package metier;

public class FormatedResponse {

	private String message;
	private Object data;
	private Boolean done;
	
	public FormatedResponse() {
	}
	
	public FormatedResponse(String message) {
		super();
		this.message = message;
	}

	public FormatedResponse(String message, Object data) {
		super();
		this.message = message;
		this.data = data;
	}
	
	public FormatedResponse(String message, Object data, Boolean done) {
		super();
		this.message = message;
		this.data = data;
		this.done = done;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return "FormatedResponse [message=" + message + ", data=" + data + ", done=" + done + "]";
	}
}
