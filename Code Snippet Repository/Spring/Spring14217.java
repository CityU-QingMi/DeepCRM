	@Test
	public void transportUrl() throws Exception {
		testTransportUrl("http", "http", TransportType.XHR_STREAMING);
		testTransportUrl("http", "ws", TransportType.WEBSOCKET);
		testTransportUrl("https", "https", TransportType.XHR_STREAMING);
		testTransportUrl("https", "wss", TransportType.WEBSOCKET);
		testTransportUrl("ws", "http", TransportType.XHR_STREAMING);
		testTransportUrl("ws", "ws", TransportType.WEBSOCKET);
		testTransportUrl("wss", "https", TransportType.XHR_STREAMING);
		testTransportUrl("wss", "wss", TransportType.WEBSOCKET);
	}
