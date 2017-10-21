	@Test
	public void clientInboundChannelSendMessage() throws Exception {
		ApplicationContext config = createConfig(TestChannelConfig.class, TestConfigurer.class);
		TestChannel channel = config.getBean("clientInboundChannel", TestChannel.class);
		SubProtocolWebSocketHandler webSocketHandler = config.getBean(SubProtocolWebSocketHandler.class);

		List<ChannelInterceptor> interceptors = channel.getInterceptors();
		assertEquals(ImmutableMessageChannelInterceptor.class, interceptors.get(interceptors.size()-1).getClass());

		TestWebSocketSession session = new TestWebSocketSession("s1");
		session.setOpen(true);
		webSocketHandler.afterConnectionEstablished(session);

		TextMessage textMessage = StompTextMessageBuilder.create(StompCommand.SEND).headers("destination:/foo").build();
		webSocketHandler.handleMessage(session, textMessage);

		Message<?> message = channel.messages.get(0);
		StompHeaderAccessor accessor = StompHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		assertNotNull(accessor);
		assertFalse(accessor.isMutable());
		assertEquals(SimpMessageType.MESSAGE, accessor.getMessageType());
		assertEquals("/foo", accessor.getDestination());
	}
