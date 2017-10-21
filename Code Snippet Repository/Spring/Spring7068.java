	@Test
	public void illegalArgumentExceptionIsNotFatal() throws JMSException {
		Message<String> message = initBuilder()
				.setHeader("foo", 123)
				.setHeader("bad", 456)
				.setHeader("bar", 789)
				.build();
		javax.jms.Message jmsMessage = new StubTextMessage() {
			@Override
			public void setObjectProperty(String name, Object value) throws JMSException {
				if (name.equals("bad")) {
					throw new IllegalArgumentException("illegal property");
				}
				super.setObjectProperty(name, value);
			}
		};
		mapper.fromHeaders(message.getHeaders(), jmsMessage);
		Object foo = jmsMessage.getObjectProperty("foo");
		assertNotNull(foo);
		Object bar = jmsMessage.getObjectProperty("bar");
		assertNotNull(bar);
		Object bad = jmsMessage.getObjectProperty("bad");
		assertNull(bad);
	}
