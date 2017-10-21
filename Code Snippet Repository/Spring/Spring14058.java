	private void testChannel(String channelName, List<Class<? extends  MessageHandler>> subscriberTypes,
			int interceptorCount) {

		AbstractSubscribableChannel channel = this.appContext.getBean(channelName, AbstractSubscribableChannel.class);

		for (Class<? extends  MessageHandler> subscriberType : subscriberTypes) {
			MessageHandler subscriber = this.appContext.getBean(subscriberType);
			assertNotNull("No subsription for " + subscriberType, subscriber);
			assertTrue(channel.hasSubscription(subscriber));
		}

		List<ChannelInterceptor> interceptors = channel.getInterceptors();
		assertEquals(interceptorCount, interceptors.size());
		assertEquals(ImmutableMessageChannelInterceptor.class, interceptors.get(interceptors.size()-1).getClass());
	}
