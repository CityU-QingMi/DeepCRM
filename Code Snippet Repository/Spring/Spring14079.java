	@Test
	public void handshakeHandler() {
		WebSocketHandler handler = new TextWebSocketHandler();
		HandshakeHandler handshakeHandler = new DefaultHandshakeHandler();

		this.registration.addHandler(handler, "/foo").setHandshakeHandler(handshakeHandler);

		List<Mapping> mappings = this.registration.getMappings();
		assertEquals(1, mappings.size());

		Mapping mapping = mappings.get(0);
		assertEquals(handler, mapping.webSocketHandler);
		assertEquals("/foo", mapping.path);
		assertSame(handshakeHandler, mapping.handshakeHandler);
	}
