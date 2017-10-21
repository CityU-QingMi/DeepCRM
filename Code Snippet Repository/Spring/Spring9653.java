	public ServletRequestHandledEvent(Object source, String requestUrl,
			String clientAddress, String method, String servletName, @Nullable String sessionId,
			@Nullable String userName, long processingTimeMillis, @Nullable Throwable failureCause) {

		super(source, sessionId, userName, processingTimeMillis, failureCause);
		this.requestUrl = requestUrl;
		this.clientAddress = clientAddress;
		this.method = method;
		this.servletName = servletName;
		this.statusCode = -1;
	}
