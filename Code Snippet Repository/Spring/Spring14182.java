	@Test
	public void originValueNoMatch() throws Exception {
		Map<String, Object> attributes = new HashMap<>();
		WebSocketHandler wsHandler = Mockito.mock(WebSocketHandler.class);
		this.servletRequest.addHeader(HttpHeaders.ORIGIN, "http://mydomain1.com");
		List<String> allowed = Collections.singletonList("http://mydomain2.com");
		OriginHandshakeInterceptor interceptor = new OriginHandshakeInterceptor(allowed);
		assertFalse(interceptor.beforeHandshake(request, response, wsHandler, attributes));
		assertEquals(servletResponse.getStatus(), HttpStatus.FORBIDDEN.value());
	}
