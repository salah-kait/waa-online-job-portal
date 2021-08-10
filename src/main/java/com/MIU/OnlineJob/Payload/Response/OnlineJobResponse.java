package com.MIU.OnlineJob.Payload.Response;

public class OnlineJobResponse<T> {
	private Boolean success;
	private String message;
	private T data;

	public OnlineJobResponse(Boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public OnlineJobResponse(Boolean success, String message, T data) {
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
