	@Test
	public void clientEndpointConfigWithUserProperties() throws Exception {

		Map<String,Object> userProperties = Collections.singletonMap("foo", "bar");

		URI uri = new URI("ws://localhost/abc");
		this.wsClient.setUserProperties(userProperties);
		this.wsClient.doHandshake(this.wsHandler, this.headers, uri).get();

		ArgumentCaptor<ClientEndpointConfig> captor = ArgumentCaptor.forClass(ClientEndpointConfig.class);
		verify(this.wsContainer).connectToServer(any(Endpoint.class), captor.capture(), any(URI.class));
		ClientEndpointConfig endpointConfig = captor.getValue();

		assertEquals(userProperties, endpointConfig.getUserProperties());
	}
