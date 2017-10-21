	private boolean checkSameOrigin(String serverName, int port, String originHeader) {
		MockHttpServletRequest servletRequest = new MockHttpServletRequest();
		ServerHttpRequest request = new ServletServerHttpRequest(servletRequest);
		servletRequest.setServerName(serverName);
		if (port != -1) {
			servletRequest.setServerPort(port);
		}
		request.getHeaders().set(HttpHeaders.ORIGIN, originHeader);
		return WebUtils.isSameOrigin(request);
	}
