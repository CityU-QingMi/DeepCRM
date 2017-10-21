	@Test
	public void handshakeHandlerAndInterceptor() {
		WebMvcStompWebSocketEndpointRegistration registration =
				new WebMvcStompWebSocketEndpointRegistration(new String[] {"/foo"}, this.handler, this.scheduler);

		DefaultHandshakeHandler handshakeHandler = new DefaultHandshakeHandler();
		HttpSessionHandshakeInterceptor interceptor = new HttpSessionHandshakeInterceptor();

		registration.setHandshakeHandler(handshakeHandler).addInterceptors(interceptor);

		MultiValueMap<HttpRequestHandler, String> mappings = registration.getMappings();
		assertEquals(1, mappings.size());

		Map.Entry<HttpRequestHandler, List<String>> entry = mappings.entrySet().iterator().next();
		assertEquals(Arrays.asList("/foo"), entry.getValue());

		WebSocketHttpRequestHandler requestHandler = (WebSocketHttpRequestHandler) entry.getKey();
		assertNotNull(requestHandler.getWebSocketHandler());
		assertSame(handshakeHandler, requestHandler.getHandshakeHandler());
		assertEquals(2, requestHandler.getHandshakeInterceptors().size());
		assertEquals(interceptor, requestHandler.getHandshakeInterceptors().get(0));
		assertEquals(OriginHandshakeInterceptor.class, requestHandler.getHandshakeInterceptors().get(1).getClass());
	}
