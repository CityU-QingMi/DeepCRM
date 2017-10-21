	@Test
	public void sendAndReceiveName() {
		Message<String> request = createTextMessage();
		javax.jms.Message replyJmsMessage = createJmsTextMessage();
		given(this.jmsTemplate.sendAndReceive(eq("myQueue"), any())).willReturn(replyJmsMessage);

		Message<?> actual = this.messagingTemplate.sendAndReceive("myQueue", request);
		verify(this.jmsTemplate, times(1)).sendAndReceive(eq("myQueue"), any());
		assertTextMessage(actual);
	}
