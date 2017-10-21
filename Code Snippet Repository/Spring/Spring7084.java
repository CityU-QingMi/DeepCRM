	@Test
	public void testSerializableConversion() throws JMSException {
		Session session = mock(Session.class);
		ObjectMessage message = mock(ObjectMessage.class);

		Integer content = new Integer(5);

		given(session.createObjectMessage(content)).willReturn(message);
		given(message.getObject()).willReturn(content);

		SimpleMessageConverter converter = new SimpleMessageConverter();
		Message msg = converter.toMessage(content, session);
		assertEquals(content, converter.fromMessage(msg));
	}
