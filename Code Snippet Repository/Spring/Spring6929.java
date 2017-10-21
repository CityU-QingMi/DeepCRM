	@Test
	public void validatePayloadInvalid() throws JMSException {
		DefaultMessageHandlerMethodFactory customFactory = new DefaultMessageHandlerMethodFactory();
		customFactory.setValidator(testValidator("invalid value"));

		Method method = getListenerMethod("validatePayload", String.class);
		MessagingMessageListenerAdapter listener = createInstance(customFactory, method);
		Session session = mock(Session.class);

		this.thrown.expect(ListenerExecutionFailedException.class);
		listener.onMessage(createSimpleJmsTextMessage("invalid value"), session); // test is an invalid value

	}
