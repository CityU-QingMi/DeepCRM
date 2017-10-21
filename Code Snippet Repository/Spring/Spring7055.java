	@Test
	public void jmsReplyToMappedFromHeader() throws JMSException {
		Destination replyTo = new Destination() {};
		Message<String> message = initBuilder()
				.setHeader(JmsHeaders.REPLY_TO, replyTo).build();

		javax.jms.Message jmsMessage = new StubTextMessage();
		mapper.fromHeaders(message.getHeaders(), jmsMessage);
		assertNotNull(jmsMessage.getJMSReplyTo());
		assertSame(replyTo, jmsMessage.getJMSReplyTo());
	}
