	@Test
	public void handshakeHandlerPassedToSockJsRegistration() {
		WebSocketHandler handler = new TextWebSocketHandler();
		HandshakeHandler handshakeHandler = new DefaultHandshakeHandler();

		this.registration.addHandler(handler, "/foo").setHandshakeHandler(handshakeHandler).withSockJS();
		this.registration.getSockJsServiceRegistration().setTaskScheduler(this.taskScheduler);

		List<Mapping> mappings = this.registration.getMappings();
		assertEquals(1, mappings.size());

		Mapping mapping = mappings.get(0);
		assertEquals(handler, mapping.webSocketHandler);
		assertEquals("/foo/**", mapping.path);
		assertNotNull(mapping.sockJsService);

		WebSocketTransportHandler transportHandler =
				(WebSocketTransportHandler) mapping.sockJsService.getTransportHandlers().get(TransportType.WEBSOCKET);
		assertSame(handshakeHandler, transportHandler.getHandshakeHandler());
	}
