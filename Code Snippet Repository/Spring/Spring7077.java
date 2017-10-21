	@Test
	public void userDefinedPropertyMappedFromHeader() throws JMSException {
		Message<String> message = initBuilder()
				.setHeader("foo", 123)
				.build();
		javax.jms.Message jmsMessage = new StubTextMessage();
		mapper.fromHeaders(message.getHeaders(), jmsMessage);
		Object value = jmsMessage.getObjectProperty("foo");
		assertNotNull(value);
		assertEquals(Integer.class, value.getClass());
		assertEquals(123, ((Integer) value).intValue());
	}
