	@Test
	public void sendAndReceive() {
		Destination destination = new Destination() {};
		Message<String> request = createTextMessage();
		javax.jms.Message replyJmsMessage = createJmsTextMessage();
		given(this.jmsTemplate.sendAndReceive(eq(destination), any())).willReturn(replyJmsMessage);

		Message<?> actual = this.messagingTemplate.sendAndReceive(destination, request);
		verify(this.jmsTemplate, times(1)).sendAndReceive(eq(destination), any());
		assertTextMessage(actual);
	}
