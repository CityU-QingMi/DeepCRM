	@Test
	public void processAndReplyWithSendToTopic() throws JMSException {
		String methodName = "processAndReplyWithSendTo";
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setPubSubDomain(true);
		MessagingMessageListenerAdapter listener = createInstance(this.factory,
				getListenerMethod(methodName, String.class), container);
		processAndReplyWithSendTo(listener, "replyDestination", true);
		assertListenerMethodInvocation(this.sample, methodName);
	}
