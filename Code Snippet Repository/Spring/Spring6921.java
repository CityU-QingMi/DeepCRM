	@Test
	public void processFromTopicAndReplyWithSendToQueue() throws JMSException {
		String methodName = "processAndReplyWithSendTo";
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setPubSubDomain(true);
		container.setReplyPubSubDomain(false);
		MessagingMessageListenerAdapter listener = createInstance(this.factory,
				getListenerMethod(methodName, String.class), container);
		processAndReplyWithSendTo(listener, "replyDestination", false);
		assertListenerMethodInvocation(this.sample, methodName);
	}
