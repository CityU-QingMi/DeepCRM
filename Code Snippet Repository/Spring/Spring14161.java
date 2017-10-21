	@Test
	public void supportedExtensions() throws Exception {

		WebSocketExtension extension1 = new WebSocketExtension("ext1");
		WebSocketExtension extension2 = new WebSocketExtension("ext2");

		given(this.upgradeStrategy.getSupportedVersions()).willReturn(new String[] {"13"});
		given(this.upgradeStrategy.getSupportedExtensions(this.request)).willReturn(Arrays.asList(extension1));

		this.servletRequest.setMethod("GET");

		WebSocketHttpHeaders headers = new WebSocketHttpHeaders(this.request.getHeaders());
		headers.setUpgrade("WebSocket");
		headers.setConnection("Upgrade");
		headers.setSecWebSocketVersion("13");
		headers.setSecWebSocketKey("82/ZS2YHjEnUN97HLL8tbw==");
		headers.setSecWebSocketExtensions(Arrays.asList(extension1, extension2));

		WebSocketHandler handler = new TextWebSocketHandler();
		Map<String, Object> attributes = Collections.<String, Object>emptyMap();
		this.handshakeHandler.doHandshake(this.request, this.response, handler, attributes);

		verify(this.upgradeStrategy).upgrade(this.request, this.response, null, Arrays.asList(extension1),
				null, handler, attributes);
	}
