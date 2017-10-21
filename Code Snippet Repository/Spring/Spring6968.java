	@Test
	public void receiveAndConvertDefaultDestinationName() {
		this.messagingTemplate.setDefaultDestinationName("myQueue");
		javax.jms.Message jmsMessage = createJmsTextMessage("my Payload");
		given(this.jmsTemplate.receive("myQueue")).willReturn(jmsMessage);

		String payload = this.messagingTemplate.receiveAndConvert(String.class);
		assertEquals("my Payload", payload);
		verify(this.jmsTemplate).receive("myQueue");
	}
