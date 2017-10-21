	@Test
	public void testTemplateExecuteInputOutput() throws ResourceException {
		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		Connection connection = mock(Connection.class);
		Interaction interaction = mock(Interaction.class);

		Record inputRecord = mock(Record.class);
		Record outputRecord = mock(Record.class);

		InteractionSpec interactionSpec = mock(InteractionSpec.class);

		given(connectionFactory.getConnection()).willReturn(connection);
		given(connection.createInteraction()).willReturn(interaction);
		given(interaction.execute(interactionSpec, inputRecord, outputRecord)).willReturn(true);


		CciTemplate ct = new CciTemplate(connectionFactory);
		ct.execute(interactionSpec, inputRecord, outputRecord);

		verify(interaction).execute(interactionSpec, inputRecord, outputRecord);
		verify(interaction).close();
		verify(connection).close();
	}
