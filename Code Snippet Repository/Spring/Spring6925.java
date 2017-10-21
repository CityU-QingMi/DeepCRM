	@Test
	public void processAndReplyWithNullReplyQosSettings() throws JMSException {
		String methodName = "processAndReplyWithSendTo";
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setReplyQosSettings(null);
		MessagingMessageListenerAdapter listener = createInstance(this.factory,
				getListenerMethod(methodName, String.class), container);
		processAndReplyWithSendTo(listener, "replyDestination", false);
		assertListenerMethodInvocation(this.sample, methodName);
	}
