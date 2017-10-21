	@Test
	public void userDefinedPropertyMappedFromHeaderWithCustomPrefix() throws JMSException {
		Message<String> message = initBuilder()
				.setHeader("foo", 123)
				.build();
		mapper.setOutboundPrefix("custom_");
		javax.jms.Message jmsMessage = new StubTextMessage();
		mapper.fromHeaders(message.getHeaders(), jmsMessage);
		Object value = jmsMessage.getObjectProperty("custom_foo");
		assertNotNull(value);
		assertEquals(Integer.class, value.getClass());
		assertEquals(123, ((Integer) value).intValue());
	}
