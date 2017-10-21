	@Test
	public void stompProtocolHandler() {
		this.endpointRegistry.addEndpoint("/stomp");

		Map<String, SubProtocolHandler> protocolHandlers = webSocketHandler.getProtocolHandlerMap();
		assertEquals(3, protocolHandlers.size());
		assertNotNull(protocolHandlers.get("v10.stomp"));
		assertNotNull(protocolHandlers.get("v11.stomp"));
		assertNotNull(protocolHandlers.get("v12.stomp"));
	}
