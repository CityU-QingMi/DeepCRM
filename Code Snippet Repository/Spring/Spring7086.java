	@Test
	public void testMapConversionWhereMapHasNNullForKey() throws JMSException {
		MapMessage message = mock(MapMessage.class);
		Session session = mock(Session.class);
		given(session.createMapMessage()).willReturn(message);

		Map<Object, String> content = new HashMap<>(1);
		content.put(null, "value1");

		SimpleMessageConverter converter = new SimpleMessageConverter();
		try {
			converter.toMessage(content, session);
			fail("expected MessageConversionException");
		}
		catch (MessageConversionException ex) { /* expected */ }
	}
