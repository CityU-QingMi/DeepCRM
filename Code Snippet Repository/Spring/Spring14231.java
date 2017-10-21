	@Test
	public void testFromValue() {
		assertEquals(TransportType.WEBSOCKET, TransportType.fromValue("websocket"));
		assertEquals(TransportType.XHR, TransportType.fromValue("xhr"));
		assertEquals(TransportType.XHR_SEND, TransportType.fromValue("xhr_send"));
		assertEquals(TransportType.JSONP, TransportType.fromValue("jsonp"));
		assertEquals(TransportType.JSONP_SEND, TransportType.fromValue("jsonp_send"));
		assertEquals(TransportType.XHR_STREAMING, TransportType.fromValue("xhr_streaming"));
		assertEquals(TransportType.EVENT_SOURCE, TransportType.fromValue("eventsource"));
		assertEquals(TransportType.HTML_FILE, TransportType.fromValue("htmlfile"));
	}
