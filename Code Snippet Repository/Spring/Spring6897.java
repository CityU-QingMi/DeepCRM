	private void setDefaultJcaConfig(DefaultJcaListenerContainerFactory factory) {
		factory.setDestinationResolver(this.destinationResolver);
		factory.setTransactionManager(this.transactionManager);
		factory.setMessageConverter(this.messageConverter);
		factory.setAcknowledgeMode(Session.DUPS_OK_ACKNOWLEDGE);
		factory.setPubSubDomain(true);
		factory.setReplyQosSettings(new QosSettings(1, 7, 5000));
		factory.setSubscriptionDurable(true);
		factory.setClientId("client-1234");
	}
