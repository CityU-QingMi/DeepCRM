	@Test
	public void testTemplateExecuteInputFalseTrue() throws ResourceException {
		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		Connection connection = mock(Connection.class);
		Interaction interaction = mock(Interaction.class);
		Record inputOutputRecord = mock(Record.class);
		InteractionSpec interactionSpec = mock(InteractionSpec.class);

		given(connectionFactory.getConnection()).willReturn(connection);
		given(connection.createInteraction()).willReturn(interaction);
		given(interaction.execute(interactionSpec, inputOutputRecord)).willReturn(null);

		CciTemplate ct = new CciTemplate(connectionFactory);
		Record tmpOutputRecord = ct.execute(interactionSpec, inputOutputRecord);
		assertNull(tmpOutputRecord);

		verify(interaction).execute(interactionSpec, inputOutputRecord);
		verify(interaction).close();
		verify(connection).close();
	}
