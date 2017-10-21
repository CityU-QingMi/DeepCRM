	@Test
	public void processAndReplyWithCustomReplyQosSettings() throws JMSException {
		String methodName = "processAndReplyWithSendTo";
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		QosSettings replyQosSettings = new QosSettings(1, 6, 6000);
		container.setReplyQosSettings(replyQosSettings);
		MessagingMessageListenerAdapter listener = createInstance(this.factory,
				getListenerMethod(methodName, String.class), container);
		processAndReplyWithSendTo(listener, "replyDestination", false, replyQosSettings);
		assertListenerMethodInvocation(this.sample, methodName);
	}
