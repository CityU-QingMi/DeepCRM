	@Test
	public void receiveDefaultDestination() {
		Destination destination = new Destination() {};
		this.messagingTemplate.setDefaultDestination(destination);
		javax.jms.Message jmsMessage = createJmsTextMessage();
		given(this.jmsTemplate.receive(destination)).willReturn(jmsMessage);

		Message<?> message = this.messagingTemplate.receive();
		verify(this.jmsTemplate).receive(destination);
		assertTextMessage(message);
	}
