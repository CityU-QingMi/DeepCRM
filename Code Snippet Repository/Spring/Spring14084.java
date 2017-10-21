	@Test
	public void brokerChannel() {
		ApplicationContext config = createConfig(TestChannelConfig.class, TestConfigurer.class);
		TestChannel channel = config.getBean("brokerChannel", TestChannel.class);
		Set<MessageHandler> handlers = channel.getSubscribers();

		List<ChannelInterceptor> interceptors = channel.getInterceptors();
		assertEquals(ImmutableMessageChannelInterceptor.class, interceptors.get(interceptors.size()-1).getClass());

		assertEquals(2, handlers.size());
		assertTrue(handlers.contains(config.getBean(SimpleBrokerMessageHandler.class)));
		assertTrue(handlers.contains(config.getBean(UserDestinationMessageHandler.class)));
	}
