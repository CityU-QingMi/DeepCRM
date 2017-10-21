	@Test
	public void allowedOrigins() {
		WebMvcStompWebSocketEndpointRegistration registration =
				new WebMvcStompWebSocketEndpointRegistration(new String[] {"/foo"}, this.handler, this.scheduler);

		registration.setAllowedOrigins();

		MultiValueMap<HttpRequestHandler, String> mappings = registration.getMappings();
		assertEquals(1, mappings.size());
		WebSocketHttpRequestHandler requestHandler = (WebSocketHttpRequestHandler)mappings.entrySet().iterator().next().getKey();
		assertNotNull(requestHandler.getWebSocketHandler());
		assertEquals(1, requestHandler.getHandshakeInterceptors().size());
		assertEquals(OriginHandshakeInterceptor.class, requestHandler.getHandshakeInterceptors().get(0).getClass());
	}
