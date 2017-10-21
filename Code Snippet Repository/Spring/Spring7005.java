	private ConnectionFactory createFailingContainerFactory() {
		try {
			ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
			given(connectionFactory.createConnection()).will(new Answer<Object>() {
				@Override
				public Object answer(InvocationOnMock invocation) throws Throwable {
					throw new JMSException("Test exception");
				}
			});
			return connectionFactory;
		}
		catch (JMSException ex) {
			throw new IllegalStateException(ex);  // never happen
		}
	}
