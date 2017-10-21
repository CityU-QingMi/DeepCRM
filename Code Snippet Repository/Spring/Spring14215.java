	@Test
	public void connectAndUseSubsetOfHandshakeHeadersForHttpRequests() throws Exception {
		ArgumentCaptor<HttpHeaders> headersCaptor = setupInfoRequest(false);
		this.xhrTransport.setStreamingDisabled(true);

		WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
		headers.set("foo", "bar");
		headers.set("auth", "123");
		this.sockJsClient.setHttpHeaderNames("auth");
		this.sockJsClient.doHandshake(handler, headers, new URI(URL)).addCallback(this.connectCallback);

		assertEquals(1, headersCaptor.getValue().size());
		assertEquals("123", headersCaptor.getValue().getFirst("auth"));
		assertEquals(1, this.xhrTransport.getRequest().getHttpRequestHeaders().size());
		assertEquals("123", this.xhrTransport.getRequest().getHttpRequestHeaders().getFirst("auth"));
	}
