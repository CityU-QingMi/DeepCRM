	@SuppressWarnings("")
	@Test
	public void testTemplateExecuteInputGeneratorExtractorFalse()
			throws ResourceException, SQLException {
		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		RecordFactory recordFactory = mock(RecordFactory.class);
		Connection connection = mock(Connection.class);
		Interaction interaction = mock(Interaction.class);
		RecordCreator generator = mock(RecordCreator.class);
		RecordExtractor<Object> extractor = mock(RecordExtractor.class);

		Record inputRecord = mock(Record.class);
		Record outputRecord = mock(Record.class);

		InteractionSpec interactionSpec = mock(InteractionSpec.class);

		given(connectionFactory.getRecordFactory()).willReturn(recordFactory);
		given(connectionFactory.getConnection()).willReturn(connection);
		given(connection.createInteraction()).willReturn(interaction);
		given(generator.createRecord(recordFactory)).willReturn(inputRecord);
		given(interaction.execute(interactionSpec, inputRecord)).willReturn(outputRecord);
		given(extractor.extractData(outputRecord)).willReturn(new Object());

		CciTemplate ct = new CciTemplate(connectionFactory);
		ct.execute(interactionSpec, generator, extractor);

		verify(extractor).extractData(outputRecord);
		verify(interaction).close();
		verify(connection).close();
	}
