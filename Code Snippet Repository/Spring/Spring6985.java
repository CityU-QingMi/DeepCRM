	@Test
	public void convertAndSendDefaultDestination() throws JMSException {
		Destination destination = new Destination() {};
		this.messagingTemplate.setDefaultDestination(destination);

		this.messagingTemplate.convertAndSend("my Payload");
		verify(this.jmsTemplate).send(eq(destination), this.messageCreator.capture());
		TextMessage textMessage = createTextMessage(this.messageCreator.getValue());
		assertEquals("my Payload", textMessage.getText());
	}
