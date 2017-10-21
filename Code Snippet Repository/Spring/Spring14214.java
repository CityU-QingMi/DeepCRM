	@Test
	public void connectWithHandshakeHeaders() throws Exception {
		ArgumentCaptor<HttpHeaders> headersCaptor = setupInfoRequest(false);
		this.xhrTransport.setStreamingDisabled(true);

		WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
		headers.set("foo", "bar");
		headers.set("auth", "123");
		this.sockJsClient.doHandshake(handler, headers, new URI(URL)).addCallback(this.connectCallback);

		HttpHeaders httpHeaders = headersCaptor.getValue();
		assertEquals(2, httpHeaders.size());
		assertEquals("bar", httpHeaders.getFirst("foo"));
		assertEquals("123", httpHeaders.getFirst("auth"));

		httpHeaders = this.xhrTransport.getRequest().getHttpRequestHeaders();
		assertEquals(2, httpHeaders.size());
		assertEquals("bar", httpHeaders.getFirst("foo"));
		assertEquals("123", httpHeaders.getFirst("auth"));
	}
