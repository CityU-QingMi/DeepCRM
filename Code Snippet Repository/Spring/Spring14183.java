	@Test
	public void originListMatch() throws Exception {
		Map<String, Object> attributes = new HashMap<>();
		WebSocketHandler wsHandler = Mockito.mock(WebSocketHandler.class);
		this.servletRequest.addHeader(HttpHeaders.ORIGIN, "http://mydomain2.com");
		List<String> allowed = Arrays.asList("http://mydomain1.com", "http://mydomain2.com", "http://mydomain3.com");
		OriginHandshakeInterceptor interceptor = new OriginHandshakeInterceptor(allowed);
		assertTrue(interceptor.beforeHandshake(request, response, wsHandler, attributes));
		assertNotEquals(servletResponse.getStatus(), HttpStatus.FORBIDDEN.value());
	}
