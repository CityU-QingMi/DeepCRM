	@Test
	public void testSimpleRecordOperationWithInputOutputRecord() throws ResourceException {
		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		Connection connection = mock(Connection.class);
		Interaction interaction = mock(Interaction.class);

		Record inputOutputRecord = mock(Record.class);

		InteractionSpec interactionSpec = mock(InteractionSpec.class);

		SimpleRecordOperation query = new SimpleRecordOperation(connectionFactory, interactionSpec);

		given(connectionFactory.getConnection()).willReturn(connection);
		given(connection.createInteraction()).willReturn(interaction);
		given(interaction.execute(interactionSpec, inputOutputRecord, inputOutputRecord)).willReturn(true);

		query.execute(inputOutputRecord, inputOutputRecord);

		verify(interaction).execute(interactionSpec, inputOutputRecord, inputOutputRecord);
		verify(interaction).close();
		verify(connection).close();
	}
