	@Test
	public void testMappingRecordOperation() throws ResourceException {
		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		Connection connection = mock(Connection.class);
		Interaction interaction = mock(Interaction.class);
		RecordFactory recordFactory = mock(RecordFactory.class);

		Record inputRecord = mock(Record.class);
		Record outputRecord = mock(Record.class);

		InteractionSpec interactionSpec = mock(InteractionSpec.class);

		QueryCallDetector callDetector = mock(QueryCallDetector.class);

		MappingRecordOperationImpl query = new MappingRecordOperationImpl(connectionFactory, interactionSpec);
		query.setCallDetector(callDetector);

		Object inObj = new Object();
		Object outObj = new Object();

		given(connectionFactory.getRecordFactory()).willReturn(recordFactory);
		given(callDetector.callCreateInputRecord(recordFactory, inObj)).willReturn(inputRecord);
		given(connectionFactory.getConnection()).willReturn(connection);
		given(connection.createInteraction()).willReturn(interaction);
		given(interaction.execute(interactionSpec, inputRecord)).willReturn(outputRecord);
		given(callDetector.callExtractOutputData(outputRecord)).willReturn(outObj);

		assertSame(outObj, query.execute(inObj));
		verify(interaction).close();
		verify(connection).close();
	}
