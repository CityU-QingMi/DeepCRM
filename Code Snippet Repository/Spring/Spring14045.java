	@Test
	public void handshakeHeaders() throws Exception {

		URI uri = new URI("ws://localhost/abc");
		List<String> protocols = Collections.singletonList("abc");
		this.headers.setSecWebSocketProtocol(protocols);
		this.headers.add("foo", "bar");

		WebSocketSession session = this.wsClient.doHandshake(this.wsHandler, this.headers, uri).get();

		assertEquals(1, session.getHandshakeHeaders().size());
		assertEquals("bar", session.getHandshakeHeaders().getFirst("foo"));
	}
