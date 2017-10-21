	private ConnectionFactory createRecoverableContainerFactory(final int failingAttempts) {
		try {
			ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
			given(connectionFactory.createConnection()).will(new Answer<Object>() {
				int currentAttempts = 0;
				@Override
				public Object answer(InvocationOnMock invocation) throws Throwable {
					currentAttempts++;
					if (currentAttempts <= failingAttempts) {
						throw new JMSException("Test exception (attempt " + currentAttempts + ")");
					}
					else {
						return mock(Connection.class);
					}
				}
			});
			return connectionFactory;
		}
		catch (JMSException ex) {
			throw new IllegalStateException(ex);  // never happen
		}
	}
