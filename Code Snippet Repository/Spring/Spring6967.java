	@Test
	public void receiveAndConvertDefaultDestination() {
		Destination destination = new Destination() {};
		this.messagingTemplate.setDefaultDestination(destination);
		javax.jms.Message jmsMessage = createJmsTextMessage("my Payload");
		given(this.jmsTemplate.receive(destination)).willReturn(jmsMessage);

		String payload = this.messagingTemplate.receiveAndConvert(String.class);
		assertEquals("my Payload", payload);
		verify(this.jmsTemplate).receive(destination);
	}
