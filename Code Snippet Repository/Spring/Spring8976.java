	@SuppressWarnings("")
	@Test
	public void testTemplateExecuteInputGeneratorExtractorTrueWithCreator()
			throws ResourceException, SQLException {
		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		RecordFactory recordFactory = mock(RecordFactory.class);
		Connection connection = mock(Connection.class);
		Interaction interaction = mock(Interaction.class);
		RecordCreator generator = mock(RecordCreator.class);
		RecordExtractor<Object> extractor = mock(RecordExtractor.class);
		RecordCreator creator = mock(RecordCreator.class);

		Record inputRecord = mock(Record.class);
		Record outputRecord = mock(Record.class);

		Object obj = new Object();

		InteractionSpec interactionSpec = mock(InteractionSpec.class);

		given(connectionFactory.getRecordFactory()).willReturn(recordFactory);
		given(connectionFactory.getConnection()).willReturn(connection);
		given(connection.createInteraction()).willReturn(interaction);
		given(creator.createRecord(recordFactory)).willReturn(outputRecord);
		given(connectionFactory.getRecordFactory()).willReturn(recordFactory);
		given(generator.createRecord(recordFactory)).willReturn(inputRecord);
		given(interaction.execute(interactionSpec, inputRecord, outputRecord)).willReturn(true);
		given(extractor.extractData(outputRecord)).willReturn(obj);

		CciTemplate ct = new CciTemplate(connectionFactory);
		ct.setOutputRecordCreator(creator);
		assertEquals(obj, ct.execute(interactionSpec, generator, extractor));

		verify(interaction).close();
		verify(connection).close();
	}
