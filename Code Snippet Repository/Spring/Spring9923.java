	public static boolean isSameOrigin(HttpRequest request) {
		String origin = request.getHeaders().getOrigin();
		if (origin == null) {
			return true;
		}
		UriComponentsBuilder urlBuilder;
		if (request instanceof ServletServerHttpRequest) {
			// Build more efficiently if we can: we only need scheme, host, port for origin comparison
			HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
			urlBuilder = new UriComponentsBuilder().
					scheme(servletRequest.getScheme()).
					host(servletRequest.getServerName()).
					port(servletRequest.getServerPort()).
					adaptFromForwardedHeaders(request.getHeaders());
		}
		else {
			urlBuilder = UriComponentsBuilder.fromHttpRequest(request);
		}
		UriComponents actualUrl = urlBuilder.build();
		UriComponents originUrl = UriComponentsBuilder.fromOriginHeader(origin).build();
		return (ObjectUtils.nullSafeEquals(actualUrl.getHost(), originUrl.getHost()) &&
				getPort(actualUrl) == getPort(originUrl));
	}
