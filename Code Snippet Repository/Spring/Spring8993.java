	@Test
	public void testSimpleRecordOperation() throws ResourceException {
		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		Connection connection = mock(Connection.class);
		Interaction interaction = mock(Interaction.class);

		Record inputRecord = mock(Record.class);
		Record outputRecord = mock(Record.class);

		InteractionSpec interactionSpec = mock(InteractionSpec.class);

		SimpleRecordOperation query = new SimpleRecordOperation(connectionFactory, interactionSpec);

		given(connectionFactory.getConnection()).willReturn(connection);
		given(connection.createInteraction()).willReturn(interaction);
		given(interaction.execute(interactionSpec, inputRecord)).willReturn(outputRecord);

		query.execute(inputRecord);

		verify(interaction).execute(interactionSpec, inputRecord);
		verify(interaction).close();
		verify(connection).close();
	}
