	public DefaultServerHttpRequestBuilder(ServerHttpRequest original) {
		Assert.notNull(original, "ServerHttpRequest is required");

		this.uri = original.getURI();
		this.httpMethodValue = original.getMethodValue();
		this.remoteAddress = original.getRemoteAddress();
		this.body = original.getBody();

		this.httpHeaders = new HttpHeaders();
		copyMultiValueMap(original.getHeaders(), this.httpHeaders);

		this.cookies = new LinkedMultiValueMap<>(original.getCookies().size());
		copyMultiValueMap(original.getCookies(), this.cookies);

		this.originalRequest = original;
	}
