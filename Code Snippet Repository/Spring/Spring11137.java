	public WebClientResponseException(String message, int statusCode, String statusText,
			@Nullable HttpHeaders headers, @Nullable byte[] responseBody,
			@Nullable Charset responseCharset) {

		super(message);

		this.statusCode = statusCode;
		this.statusText = statusText;
		this.headers = (headers != null ? headers : HttpHeaders.EMPTY);
		this.responseBody = (responseBody != null ? responseBody : new byte[0]);
		this.responseCharset = (responseCharset != null ? responseCharset : StandardCharsets.ISO_8859_1);
	}
