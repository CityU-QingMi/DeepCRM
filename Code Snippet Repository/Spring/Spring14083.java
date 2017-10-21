	@Test
	public void clientOutboundChannel() {
		ApplicationContext config = createConfig(TestChannelConfig.class, TestConfigurer.class);
		TestChannel channel = config.getBean("clientOutboundChannel", TestChannel.class);
		Set<MessageHandler> handlers = channel.getSubscribers();

		List<ChannelInterceptor> interceptors = channel.getInterceptors();
		assertEquals(ImmutableMessageChannelInterceptor.class, interceptors.get(interceptors.size()-1).getClass());

		assertEquals(1, handlers.size());
		assertTrue(handlers.contains(config.getBean(SubProtocolWebSocketHandler.class)));
	}
