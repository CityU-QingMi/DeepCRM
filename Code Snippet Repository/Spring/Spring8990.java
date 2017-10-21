	@SuppressWarnings("")
	@Test
	public void testTemplateExecuteInputExtractorTrueWithCreator()
			throws ResourceException, SQLException {
		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		RecordFactory recordFactory = mock(RecordFactory.class);
		Connection connection = mock(Connection.class);
		Interaction interaction = mock(Interaction.class);
		RecordExtractor<Object> extractor = mock(RecordExtractor.class);
		RecordCreator creator = mock(RecordCreator.class);

		Record inputRecord = mock(Record.class);
		Record outputRecord = mock(Record.class);

		InteractionSpec interactionSpec = mock(InteractionSpec.class);

		given(connectionFactory.getConnection()).willReturn(connection);
		given(connection.createInteraction()).willReturn(interaction);
		given(connectionFactory.getRecordFactory()).willReturn(recordFactory);
		given(creator.createRecord(recordFactory)).willReturn(outputRecord);
		given(interaction.execute(interactionSpec, inputRecord, outputRecord)).willReturn(true);
		given(extractor.extractData(outputRecord)).willReturn(new Object());

		CciTemplate ct = new CciTemplate(connectionFactory);
		ct.setOutputRecordCreator(creator);
		ct.execute(interactionSpec, inputRecord, extractor);

		verify(extractor).extractData(outputRecord);
		verify(interaction).close();
		verify(connection).close();
	}
