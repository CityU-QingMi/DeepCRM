	@Test
	@SuppressWarnings("")
	public void afterSessionInitializedOpenFrameFirst() throws Exception {
		TextWebSocketHandler handler = new TextWebSocketHandler() {
			@Override
			public void afterConnectionEstablished(WebSocketSession session) throws Exception {
				session.sendMessage(new TextMessage("go go"));
			}
		};
		TestWebSocketServerSockJsSession session = new TestWebSocketServerSockJsSession(this.sockJsConfig, handler, null);
		session.initializeDelegateSession(this.webSocketSession);
		List<TextMessage> expected = Arrays.asList(new TextMessage("o"), new TextMessage("a[\"go go\"]"));
		assertEquals(expected, this.webSocketSession.getSentMessages());
	}
