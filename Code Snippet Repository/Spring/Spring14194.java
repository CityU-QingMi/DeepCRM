	@Test
	public void echoXhrWithHeaders() throws Exception {
		AbstractXhrTransport xhrTransport = createXhrTransport();
		xhrTransport.setXhrStreamingDisabled(true);

		WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
		headers.add("auth", "123");
		testEcho(10, xhrTransport, headers);

		for (Map.Entry<String, HttpHeaders> entry : this.testFilter.requests.entrySet()) {
			HttpHeaders httpHeaders = entry.getValue();
			assertEquals("No auth header for: " + entry.getKey(), "123", httpHeaders.getFirst("auth"));
		}
	}
