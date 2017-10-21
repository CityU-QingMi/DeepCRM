	private ConnectionFactory createSuccessfulConnectionFactory() {
		try {
			ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
			given(connectionFactory.createConnection()).willReturn(mock(Connection.class));
			return connectionFactory;
		}
		catch (JMSException ex) {
			throw new IllegalStateException(ex);  // never happen
		}
	}
