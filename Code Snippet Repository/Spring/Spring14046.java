	@Test
	public void clientEndpointConfig() throws Exception {

		URI uri = new URI("ws://localhost/abc");
		List<String> protocols = Collections.singletonList("abc");
		this.headers.setSecWebSocketProtocol(protocols);

		this.wsClient.doHandshake(this.wsHandler, this.headers, uri).get();

		ArgumentCaptor<ClientEndpointConfig> captor = ArgumentCaptor.forClass(ClientEndpointConfig.class);
		verify(this.wsContainer).connectToServer(any(Endpoint.class), captor.capture(), any(URI.class));
		ClientEndpointConfig endpointConfig = captor.getValue();

		assertEquals(protocols, endpointConfig.getPreferredSubprotocols());
	}
