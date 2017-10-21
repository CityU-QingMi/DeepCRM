	private void setDefaultJmsConfig(AbstractJmsListenerContainerFactory<?> factory) {
		factory.setConnectionFactory(this.connectionFactory);
		factory.setDestinationResolver(this.destinationResolver);
		factory.setMessageConverter(this.messageConverter);
		factory.setSessionTransacted(true);
		factory.setSessionAcknowledgeMode(Session.DUPS_OK_ACKNOWLEDGE);
		factory.setPubSubDomain(true);
		factory.setReplyPubSubDomain(true);
		factory.setReplyQosSettings(new QosSettings(1, 7, 5000));
		factory.setSubscriptionDurable(true);
		factory.setClientId("client-1234");
		factory.setAutoStartup(false);
	}
