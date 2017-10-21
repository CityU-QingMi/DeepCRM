	@Test
	public void sendDefaultDestination() {
		Destination destination = new Destination() {};
		this.messagingTemplate.setDefaultDestination(destination);
		Message<String> message = createTextMessage();

		this.messagingTemplate.send(message);
		verify(this.jmsTemplate).send(eq(destination), this.messageCreator.capture());
		assertTextMessage(this.messageCreator.getValue());
	}
