	@SuppressWarnings("")
	@Test
	public void testTemplateExecuteInputOutputResultsSetFalse()
			throws ResourceException, SQLException {
		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		RecordFactory recordFactory = mock(RecordFactory.class);
		Connection connection = mock(Connection.class);
		Interaction interaction = mock(Interaction.class);
		Record record = mock(Record.class);
		ResultSet resultset = mock(ResultSet.class);
		RecordCreator generator = mock(RecordCreator.class);
		RecordExtractor<Object> extractor = mock(RecordExtractor.class);

		InteractionSpec interactionSpec = mock(InteractionSpec.class);

		given(connectionFactory.getRecordFactory()).willReturn(recordFactory);
		given(connectionFactory.getConnection()).willReturn(connection);
		given(connection.createInteraction()).willReturn(interaction);
		given(generator.createRecord(recordFactory)).willReturn(record);
		given(interaction.execute(interactionSpec, record)).willReturn(resultset);
		given(extractor.extractData(resultset)).willReturn(new Object());

		CciTemplate ct = new CciTemplate(connectionFactory);
		ct.execute(interactionSpec, generator, extractor);

		verify(extractor).extractData(resultset);
		verify(resultset).close();
		verify(interaction).close();
		verify(connection).close();
	}
