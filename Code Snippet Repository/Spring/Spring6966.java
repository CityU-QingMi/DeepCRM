	@Test
	public void receiveAndConvert() {
		Destination destination = new Destination() {};
		javax.jms.Message jmsMessage = createJmsTextMessage("my Payload");
		given(this.jmsTemplate.receive(destination)).willReturn(jmsMessage);

		String payload = this.messagingTemplate.receiveAndConvert(destination, String.class);
		assertEquals("my Payload", payload);
		verify(this.jmsTemplate).receive(destination);
	}
