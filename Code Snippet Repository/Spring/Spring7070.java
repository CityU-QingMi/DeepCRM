	@Test
	public void attemptToWriteDisallowedTypePropertyIsNotFatal() throws JMSException {
		Message<String> message = initBuilder()
				.setHeader(JmsHeaders.TYPE, "someType")
				.setHeader("foo", "bar")
				.build();
		javax.jms.Message jmsMessage = new StubTextMessage() {
			@Override
			public void setJMSType(String type) throws JMSException {
				throw new JMSException("illegal property");
			}
		};
		mapper.fromHeaders(message.getHeaders(), jmsMessage);
		assertNull(jmsMessage.getJMSType());
		assertNotNull(jmsMessage.getStringProperty("foo"));
		assertEquals("bar", jmsMessage.getStringProperty("foo"));
	}
