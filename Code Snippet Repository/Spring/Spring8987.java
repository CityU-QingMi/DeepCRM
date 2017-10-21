	@Test
	public void testTemplateExecuteWithCreatorAndRecordFactoryNotSupported()
			throws ResourceException {
		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		Connection connection = mock(Connection.class);
		Interaction interaction = mock(Interaction.class);

		Record inputRecord = mock(Record.class);
		final Record outputRecord = mock(Record.class);

		InteractionSpec interactionSpec = mock(InteractionSpec.class);

		given(connectionFactory.getConnection()).willReturn(connection);
		given(connectionFactory.getRecordFactory()).willThrow(new NotSupportedException("not supported"));
		given(connection.createInteraction()).willReturn(interaction);
		given(interaction.execute(interactionSpec, inputRecord, outputRecord)).willReturn(true);

		CciTemplate ct = new CciTemplate(connectionFactory);
		ct.setOutputRecordCreator(new RecordCreator() {
			@Override
			public Record createRecord(RecordFactory recordFactory) {
				assertTrue(recordFactory instanceof NotSupportedRecordFactory);
				return outputRecord;
			}
		});
		ct.execute(interactionSpec, inputRecord);

		verify(interaction).execute(interactionSpec, inputRecord, outputRecord);
		verify(interaction).close();
		verify(connection).close();
	}
