	@Test
	public void testTemplateExecuteInputGeneratorTrueWithCreator()
			throws ResourceException {
		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		RecordFactory recordFactory = mock(RecordFactory.class);
		Connection connection = mock(Connection.class);
		Interaction interaction = mock(Interaction.class);
		RecordCreator generator = mock(RecordCreator.class);
		RecordCreator creator = mock(RecordCreator.class);

		Record inputRecord = mock(Record.class);
		Record outputRecord = mock(Record.class);

		InteractionSpec interactionSpec = mock(InteractionSpec.class);

		given(connectionFactory.getRecordFactory()).willReturn(recordFactory);
		given(generator.createRecord(recordFactory)).willReturn(inputRecord);
		given(connectionFactory.getConnection()).willReturn(connection);
		given(connection.createInteraction()).willReturn(interaction);
		given(creator.createRecord(recordFactory)).willReturn(outputRecord);
		given(connectionFactory.getRecordFactory()).willReturn(recordFactory);
		given(interaction.execute(interactionSpec, inputRecord, outputRecord)).willReturn(true);


		CciTemplate ct = new CciTemplate(connectionFactory);
		ct.setOutputRecordCreator(creator);
		ct.execute(interactionSpec, generator);

		verify(interaction).execute(interactionSpec, inputRecord, outputRecord);
		verify(interaction).close();
		verify(connection).close();
	}
