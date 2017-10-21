	@Test
	public void originMatchAll() throws Exception {
		Map<String, Object> attributes = new HashMap<>();
		WebSocketHandler wsHandler = Mockito.mock(WebSocketHandler.class);
		this.servletRequest.addHeader(HttpHeaders.ORIGIN, "http://mydomain1.com");
		OriginHandshakeInterceptor interceptor = new OriginHandshakeInterceptor();
		interceptor.setAllowedOrigins(Collections.singletonList("*"));
		assertTrue(interceptor.beforeHandshake(request, response, wsHandler, attributes));
		assertNotEquals(servletResponse.getStatus(), HttpStatus.FORBIDDEN.value());
	}
