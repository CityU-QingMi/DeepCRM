	@Test
	public void originNoMatchWithNullHostileCollection() throws Exception {
		Map<String, Object> attributes = new HashMap<>();
		WebSocketHandler wsHandler = Mockito.mock(WebSocketHandler.class);
		this.servletRequest.addHeader(HttpHeaders.ORIGIN, "http://mydomain4.com");
		OriginHandshakeInterceptor interceptor = new OriginHandshakeInterceptor();
		Set<String> allowedOrigins = new ConcurrentSkipListSet<>();
		allowedOrigins.add("http://mydomain1.com");
		interceptor.setAllowedOrigins(allowedOrigins);
		assertFalse(interceptor.beforeHandshake(request, response, wsHandler, attributes));
		assertEquals(servletResponse.getStatus(), HttpStatus.FORBIDDEN.value());
	}
