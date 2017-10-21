	@Test
	public void originListNoMatch() throws Exception {
		Map<String, Object> attributes = new HashMap<>();
		WebSocketHandler wsHandler = Mockito.mock(WebSocketHandler.class);
		this.servletRequest.addHeader(HttpHeaders.ORIGIN, "http://mydomain4.com");
		List<String> allowed = Arrays.asList("http://mydomain1.com", "http://mydomain2.com", "http://mydomain3.com");
		OriginHandshakeInterceptor interceptor = new OriginHandshakeInterceptor(allowed);
		assertFalse(interceptor.beforeHandshake(request, response, wsHandler, attributes));
		assertEquals(servletResponse.getStatus(), HttpStatus.FORBIDDEN.value());
	}
