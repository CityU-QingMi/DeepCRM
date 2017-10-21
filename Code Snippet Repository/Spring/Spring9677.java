	public static boolean isSameOrigin(ServerHttpRequest request) {
		String origin = request.getHeaders().getOrigin();
		if (origin == null) {
			return true;
		}
		UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpRequest(request);
		UriComponents actualUrl = urlBuilder.build();
		String actualHost = actualUrl.getHost();
		int actualPort = getPort(actualUrl);
		Assert.notNull(actualHost, "Actual request host must not be null");
		Assert.isTrue(actualPort != -1, "Actual request port must not be undefined");
		UriComponents originUrl = UriComponentsBuilder.fromOriginHeader(origin).build();
		return (actualHost.equals(originUrl.getHost()) && actualPort == getPort(originUrl));
	}
