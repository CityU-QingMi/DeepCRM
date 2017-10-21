	@Test
	public void minimalRegistration() {
		WebMvcStompWebSocketEndpointRegistration registration =
				new WebMvcStompWebSocketEndpointRegistration(new String[] {"/foo"}, this.handler, this.scheduler);

		MultiValueMap<HttpRequestHandler, String> mappings = registration.getMappings();
		assertEquals(1, mappings.size());

		Map.Entry<HttpRequestHandler, List<String>> entry = mappings.entrySet().iterator().next();
		assertNotNull(((WebSocketHttpRequestHandler) entry.getKey()).getWebSocketHandler());
		assertEquals(1, ((WebSocketHttpRequestHandler) entry.getKey()).getHandshakeInterceptors().size());
		assertEquals(Arrays.asList("/foo"), entry.getValue());
	}
