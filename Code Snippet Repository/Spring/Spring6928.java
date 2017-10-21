	@Test
	public void validatePayloadValid() throws JMSException {
		String methodName = "validatePayload";

		DefaultMessageHandlerMethodFactory customFactory = new DefaultMessageHandlerMethodFactory();
		customFactory.setValidator(testValidator("invalid value"));
		initializeFactory(customFactory);

		Method method = getListenerMethod(methodName, String.class);
		MessagingMessageListenerAdapter listener = createInstance(customFactory, method);
		Session session = mock(Session.class);
		listener.onMessage(createSimpleJmsTextMessage("test"), session); // test is a valid value
		assertListenerMethodInvocation(this.sample, methodName);
	}
