	@Test
	public void testTemplateExecuteInputTrueWithCreator2()
			throws ResourceException {
		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		RecordFactory recordFactory = mock(RecordFactory.class);
		Connection connection = mock(Connection.class);
		Interaction interaction = mock(Interaction.class);
		RecordCreator creator = mock(RecordCreator.class);

		Record inputRecord = mock(Record.class);
		final Record outputRecord = mock(Record.class);

		InteractionSpec interactionSpec = mock(InteractionSpec.class);

		given(connectionFactory.getConnection()).willReturn(connection);
		given(connectionFactory.getRecordFactory()).willReturn(recordFactory);
		given(connection.createInteraction()).willReturn(interaction);
		given(creator.createRecord(recordFactory)).willReturn(outputRecord);
		given(interaction.execute(interactionSpec, inputRecord, outputRecord)).willReturn(true);

		CciTemplate ct = new CciTemplate(connectionFactory);
		ct.setOutputRecordCreator(creator);
		ct.execute(interactionSpec, inputRecord);

		verify(interaction).execute(interactionSpec, inputRecord, outputRecord);
		verify(interaction).close();
		verify(connection).close();
	}
