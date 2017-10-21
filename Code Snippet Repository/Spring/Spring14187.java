	@Test
	public void sameOriginMatchWithEmptyAllowedOrigins() throws Exception {
		Map<String, Object> attributes = new HashMap<>();
		WebSocketHandler wsHandler = Mockito.mock(WebSocketHandler.class);
		this.servletRequest.addHeader(HttpHeaders.ORIGIN, "http://mydomain2.com");
		this.servletRequest.setServerName("mydomain2.com");
		OriginHandshakeInterceptor interceptor = new OriginHandshakeInterceptor(Collections.emptyList());
		assertTrue(interceptor.beforeHandshake(request, response, wsHandler, attributes));
		assertNotEquals(servletResponse.getStatus(), HttpStatus.FORBIDDEN.value());
	}
