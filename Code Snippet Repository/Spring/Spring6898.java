	private void assertDefaultJcaConfig(JmsMessageEndpointManager container) {
		assertEquals(this.messageConverter, container.getMessageConverter());
		assertEquals(this.destinationResolver, container.getDestinationResolver());
		JmsActivationSpecConfig config = container.getActivationSpecConfig();
		assertNotNull(config);
		assertEquals(Session.DUPS_OK_ACKNOWLEDGE, config.getAcknowledgeMode());
		assertEquals(true, config.isPubSubDomain());
		assertEquals(new QosSettings(1, 7, 5000), container.getReplyQosSettings());
		assertEquals(true, config.isSubscriptionDurable());
		assertEquals("client-1234", config.getClientId());
	}
