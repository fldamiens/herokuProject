package metier;

import java.util.UUID;

public class Approval {
	
	private String idAccount;
	private String name;
	private Enum<ManualResponse> manualResponse;
	
	public Approval() {}

	public Approval(String idAccount, String name, Enum<ManualResponse> manualResponse) {
		super();
		this.idAccount = idAccount;
		this.name = name;
		this.manualResponse = manualResponse;
	}

	public String getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(String idAccount) {
		this.idAccount = idAccount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Enum<ManualResponse> getManualResponse() {
		return manualResponse;
	}

	public void setManualResponse(Enum<ManualResponse> manualResponse) {
		this.manualResponse = manualResponse;
	}

	@Override
	public String toString() {
		return "Approval [idAccount=" + idAccount + ", name=" + name + ", manualResponse=" + manualResponse + "]";
	}
}
