	@Test
	public void standardWebSocketClientConfiguratorInsertsHandshakeHeaders() throws Exception {

		URI uri = new URI("ws://localhost/abc");
		this.headers.add("foo", "bar");

		this.wsClient.doHandshake(this.wsHandler, this.headers, uri).get();

		ArgumentCaptor<ClientEndpointConfig> captor = ArgumentCaptor.forClass(ClientEndpointConfig.class);
		verify(this.wsContainer).connectToServer(any(Endpoint.class), captor.capture(), any(URI.class));
		ClientEndpointConfig endpointConfig = captor.getValue();

		Map<String, List<String>> headers = new HashMap<>();
		endpointConfig.getConfigurator().beforeRequest(headers);
		assertEquals(1, headers.size());
	}
