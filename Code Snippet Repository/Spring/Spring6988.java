	@Test
	public void receive() {
		Destination destination = new Destination() {};
		javax.jms.Message jmsMessage = createJmsTextMessage();
		given(this.jmsTemplate.receive(destination)).willReturn(jmsMessage);

		Message<?> message = this.messagingTemplate.receive(destination);
		verify(this.jmsTemplate).receive(destination);
		assertTextMessage(message);
	}
