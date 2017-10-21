	@Test
	public void testTemplateExecuteInputOutputConnectionSpec() throws ResourceException {
		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		ConnectionSpec connectionSpec = mock(ConnectionSpec.class);
		Connection connection = mock(Connection.class);
		Interaction interaction = mock(Interaction.class);

		Record inputRecord = mock(Record.class);
		Record outputRecord = mock(Record.class);

		InteractionSpec interactionSpec = mock(InteractionSpec.class);

		given(connectionFactory.getConnection(connectionSpec)).willReturn(connection);
		given(connection.createInteraction()).willReturn(interaction);
		given(interaction.execute(interactionSpec, inputRecord, outputRecord)).willReturn(true);

		ConnectionSpecConnectionFactoryAdapter adapter = new ConnectionSpecConnectionFactoryAdapter();
		adapter.setTargetConnectionFactory(connectionFactory);
		adapter.setConnectionSpec(connectionSpec);
		CciTemplate ct = new CciTemplate(adapter);
		ct.execute(interactionSpec, inputRecord, outputRecord);

		verify(interaction).execute(interactionSpec, inputRecord, outputRecord);
		verify(interaction).close();
		verify(connection).close();
	}
