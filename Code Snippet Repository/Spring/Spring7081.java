	@Test
	public void testStringConversion() throws JMSException {
		Session session = mock(Session.class);
		TextMessage message = mock(TextMessage.class);

		String content = "test";

		given(session.createTextMessage(content)).willReturn(message);
		given(message.getText()).willReturn(content);

		SimpleMessageConverter converter = new SimpleMessageConverter();
		Message msg = converter.toMessage(content, session);
		assertEquals(content, converter.fromMessage(msg));
	}
