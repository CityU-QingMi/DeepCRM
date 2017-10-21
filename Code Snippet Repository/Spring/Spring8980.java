	@SuppressWarnings("")
	@Test
	public void testTemplateExecuteConnectionCallback()
			throws ResourceException, SQLException {
		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		Connection connection = mock(Connection.class);
		ConnectionCallback<Object> connectionCallback = mock(ConnectionCallback.class);

		given(connectionFactory.getConnection()).willReturn(connection);
		given(connectionCallback.doInConnection(connection, connectionFactory)).willReturn(new Object());

		CciTemplate ct = new CciTemplate(connectionFactory);
		ct.execute(connectionCallback);

		verify(connectionCallback).doInConnection(connection, connectionFactory);
		verify(connection).close();
	}
