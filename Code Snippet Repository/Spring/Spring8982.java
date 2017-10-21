	@Test
	public void testTemplateExecuteInputTrueTrueWithCreator()
			throws ResourceException {
		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		Connection connection = mock(Connection.class);
		Interaction interaction = mock(Interaction.class);
		RecordCreator creator = mock(RecordCreator.class);

		Record inputOutputRecord = mock(Record.class);

		InteractionSpec interactionSpec = mock(InteractionSpec.class);

		given(connectionFactory.getConnection()).willReturn(connection);
		given(connection.createInteraction()).willReturn(interaction);
		given(interaction.execute(interactionSpec, inputOutputRecord, inputOutputRecord)).willReturn(true);

		CciTemplate ct = new CciTemplate(connectionFactory);
		ct.setOutputRecordCreator(creator);
		ct.execute(interactionSpec, inputOutputRecord, inputOutputRecord);

		verify(interaction).execute(interactionSpec, inputOutputRecord, inputOutputRecord);
		verify(interaction).close();
		verify(connection).close();
	}
