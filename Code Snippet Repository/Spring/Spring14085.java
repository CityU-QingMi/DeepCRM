	@Test
	public void webSocketHandler() {
		ApplicationContext config = createConfig(TestChannelConfig.class, TestConfigurer.class);
		SubProtocolWebSocketHandler subWsHandler = config.getBean(SubProtocolWebSocketHandler.class);

		assertEquals(1024 * 1024, subWsHandler.getSendBufferSizeLimit());
		assertEquals(25 * 1000, subWsHandler.getSendTimeLimit());

		Map<String, SubProtocolHandler> handlerMap = subWsHandler.getProtocolHandlerMap();
		StompSubProtocolHandler protocolHandler = (StompSubProtocolHandler) handlerMap.get("v12.stomp");
		assertEquals(128 * 1024, protocolHandler.getMessageSizeLimit());
	}
