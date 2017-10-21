	private void assertDefaultJmsConfig(AbstractMessageListenerContainer container) {
		assertEquals(this.connectionFactory, container.getConnectionFactory());
		assertEquals(this.destinationResolver, container.getDestinationResolver());
		assertEquals(this.messageConverter, container.getMessageConverter());
		assertEquals(true, container.isSessionTransacted());
		assertEquals(Session.DUPS_OK_ACKNOWLEDGE, container.getSessionAcknowledgeMode());
		assertEquals(true, container.isPubSubDomain());
		assertEquals(true, container.isReplyPubSubDomain());
		assertEquals(new QosSettings(1, 7, 5000), container.getReplyQosSettings());
		assertEquals(true, container.isSubscriptionDurable());
		assertEquals("client-1234", container.getClientId());
		assertEquals(false, container.isAutoStartup());
	}
