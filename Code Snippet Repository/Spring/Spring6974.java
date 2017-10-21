	@Test
	public void convertSendAndReceivePayload() throws JMSException {
		Destination destination = new Destination() {};
		javax.jms.Message replyJmsMessage = createJmsTextMessage("My reply");
		given(this.jmsTemplate.sendAndReceive(eq(destination), any())).willReturn(replyJmsMessage);

		String reply = this.messagingTemplate.convertSendAndReceive(destination, "my Payload", String.class);
		verify(this.jmsTemplate, times(1)).sendAndReceive(eq(destination), any());
		assertEquals("My reply", reply);
	}
