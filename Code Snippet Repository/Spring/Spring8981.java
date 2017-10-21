	@SuppressWarnings("")
	@Test
	public void testTemplateExecuteInteractionCallback()
			throws ResourceException, SQLException {
		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		Connection connection = mock(Connection.class);
		Interaction interaction = mock(Interaction.class);
		InteractionCallback<Object> interactionCallback = mock(InteractionCallback.class);

		given(connectionFactory.getConnection()).willReturn(connection);
		given(connection.createInteraction()).willReturn(interaction);
		given(interactionCallback.doInInteraction(interaction,connectionFactory)).willReturn(new Object());

		CciTemplate ct = new CciTemplate(connectionFactory);
		ct.execute(interactionCallback);

		verify(interactionCallback).doInInteraction(interaction,connectionFactory);
		verify(interaction).close();
		verify(connection).close();
	}
