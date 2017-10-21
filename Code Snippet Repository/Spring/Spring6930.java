	@Test
	public void invalidPayloadType() throws JMSException {
		MessagingMessageListenerAdapter listener = createDefaultInstance(Integer.class);
		Session session = mock(Session.class);

		this.thrown.expect(ListenerExecutionFailedException.class);
		this.thrown.expectCause(Matchers.isA(MessageConversionException.class));
		this.thrown.expectMessage(getDefaultListenerMethod(Integer.class).toGenericString()); // ref to method
		listener.onMessage(createSimpleJmsTextMessage("test"), session); // test is not a valid integer
	}
