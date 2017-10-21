	@Test
	public void jmsTypeMappedFromHeader() throws JMSException {
		String jmsType = "testing";
		Message<String> message = initBuilder()
				.setHeader(JmsHeaders.TYPE, jmsType).build();
		javax.jms.Message jmsMessage = new StubTextMessage();
		mapper.fromHeaders(message.getHeaders(), jmsMessage);
		assertNotNull(jmsMessage.getJMSType());
		assertEquals(jmsType, jmsMessage.getJMSType());
	}
