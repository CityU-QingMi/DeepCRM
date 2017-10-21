	@Test
	public void convertSendAndReceiveDefaultDestinationName() throws JMSException {
		this.messagingTemplate.setDefaultDestinationName("myQueue");
		javax.jms.Message replyJmsMessage = createJmsTextMessage("My reply");
		given(this.jmsTemplate.sendAndReceive(eq("myQueue"), any())).willReturn(replyJmsMessage);

		String reply = this.messagingTemplate.convertSendAndReceive("my Payload", String.class);
		verify(this.jmsTemplate, times(1)).sendAndReceive(eq("myQueue"), any());
		assertEquals("My reply", reply);
	}
