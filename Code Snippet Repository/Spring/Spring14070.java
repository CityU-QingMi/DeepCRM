	@Test
	public void handshakeHandlerInterceptorWithSockJsService() {
		WebMvcStompWebSocketEndpointRegistration registration =
				new WebMvcStompWebSocketEndpointRegistration(new String[] {"/foo"}, this.handler, this.scheduler);

		DefaultHandshakeHandler handshakeHandler = new DefaultHandshakeHandler();
		HttpSessionHandshakeInterceptor interceptor = new HttpSessionHandshakeInterceptor();

		registration.setHandshakeHandler(handshakeHandler).addInterceptors(interceptor).withSockJS();

		MultiValueMap<HttpRequestHandler, String> mappings = registration.getMappings();
		assertEquals(1, mappings.size());

		Map.Entry<HttpRequestHandler, List<String>> entry = mappings.entrySet().iterator().next();
		assertEquals(Arrays.asList("/foo/**"), entry.getValue());

		SockJsHttpRequestHandler requestHandler = (SockJsHttpRequestHandler) entry.getKey();
		assertNotNull(requestHandler.getWebSocketHandler());

		DefaultSockJsService sockJsService = (DefaultSockJsService) requestHandler.getSockJsService();
		assertNotNull(sockJsService);

		Map<TransportType, TransportHandler> handlers = sockJsService.getTransportHandlers();
		WebSocketTransportHandler transportHandler = (WebSocketTransportHandler) handlers.get(TransportType.WEBSOCKET);
		assertSame(handshakeHandler, transportHandler.getHandshakeHandler());
		assertEquals(2, sockJsService.getHandshakeInterceptors().size());
		assertEquals(interceptor, sockJsService.getHandshakeInterceptors().get(0));
		assertEquals(OriginHandshakeInterceptor.class, sockJsService.getHandshakeInterceptors().get(1).getClass());
	}
