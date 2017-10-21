	@Test
	public void attemptToWriteDisallowedReplyToPropertyIsNotFatal() throws JMSException {
		Message<String> message = initBuilder()
				.setHeader(JmsHeaders.REPLY_TO, new Destination() {})
				.setHeader("foo", "bar")
				.build();
		javax.jms.Message jmsMessage = new StubTextMessage() {
			@Override
			public void setJMSReplyTo(Destination replyTo) throws JMSException {
				throw new JMSException("illegal property");
			}
		};
		mapper.fromHeaders(message.getHeaders(), jmsMessage);
		assertNull(jmsMessage.getJMSReplyTo());
		assertNotNull(jmsMessage.getStringProperty("foo"));
		assertEquals("bar", jmsMessage.getStringProperty("foo"));
	}
