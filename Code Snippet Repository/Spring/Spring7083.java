	@Test
	public void testMapConversion() throws JMSException {

		Session session = mock(Session.class);
		MapMessage message = mock(MapMessage.class);

		Map<String, String> content = new HashMap<>(2);
		content.put("key1", "value1");
		content.put("key2", "value2");

		given(session.createMapMessage()).willReturn(message);
		given(message.getMapNames()).willReturn(Collections.enumeration(content.keySet()));
		given(message.getObject("key1")).willReturn("value1");
		given(message.getObject("key2")).willReturn("value2");

		SimpleMessageConverter converter = new SimpleMessageConverter();
		Message msg = converter.toMessage(content, session);
		assertEquals(content, converter.fromMessage(msg));

		verify(message).setObject("key1", "value1");
		verify(message).setObject("key2", "value2");
	}
