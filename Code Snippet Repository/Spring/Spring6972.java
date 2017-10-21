	@Test
	public void sendAndReceiveDefaultDestination() {
		Destination destination = new Destination() {};
		this.messagingTemplate.setDefaultDestination(destination);
		Message<String> request = createTextMessage();
		javax.jms.Message replyJmsMessage = createJmsTextMessage();
		given(this.jmsTemplate.sendAndReceive(eq(destination), any())).willReturn(replyJmsMessage);

		Message<?> actual = this.messagingTemplate.sendAndReceive(request);
		verify(this.jmsTemplate, times(1)).sendAndReceive(eq(destination), any());
		assertTextMessage(actual);
	}
