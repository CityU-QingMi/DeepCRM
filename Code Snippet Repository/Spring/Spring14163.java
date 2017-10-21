	@Test
	public void subProtocolCapableHandlerNoMatch() throws Exception {

		given(this.upgradeStrategy.getSupportedVersions()).willReturn(new String[]{"13"});

		this.servletRequest.setMethod("GET");

		WebSocketHttpHeaders headers = new WebSocketHttpHeaders(this.request.getHeaders());
		headers.setUpgrade("WebSocket");
		headers.setConnection("Upgrade");
		headers.setSecWebSocketVersion("13");
		headers.setSecWebSocketKey("82/ZS2YHjEnUN97HLL8tbw==");
		headers.setSecWebSocketProtocol("v10.stomp");

		WebSocketHandler handler = new SubProtocolCapableHandler("v12.stomp", "v11.stomp");
		Map<String, Object> attributes = Collections.<String, Object>emptyMap();
		this.handshakeHandler.doHandshake(this.request, this.response, handler, attributes);

		verify(this.upgradeStrategy).upgrade(this.request, this.response,
				null, Collections.<WebSocketExtension>emptyList(), null, handler, attributes);
	}
