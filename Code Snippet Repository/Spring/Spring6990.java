	@Test
	public void receiveDefaultDestinationName() {
		this.messagingTemplate.setDefaultDestinationName("myQueue");
		javax.jms.Message jmsMessage = createJmsTextMessage();
		given(this.jmsTemplate.receive("myQueue")).willReturn(jmsMessage);

		Message<?> message = this.messagingTemplate.receive();
		verify(this.jmsTemplate).receive("myQueue");
		assertTextMessage(message);
	}
