	@Test
	public void contentTypePropertyMappedFromHeader() throws JMSException {
		Message<String> message = initBuilder()
				.setHeader(MessageHeaders.CONTENT_TYPE, "foo")
				.build();
		javax.jms.Message jmsMessage = new StubTextMessage();
		mapper.fromHeaders(message.getHeaders(), jmsMessage);
		Object value = jmsMessage.getObjectProperty(JmsHeaderMapper.CONTENT_TYPE_PROPERTY);
		assertNotNull(value);
		assertEquals("foo", value);
	}
