	@Test
	public void testLocalTransactionCommit() throws ResourceException {
		final ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		Connection connection = mock(Connection.class);
		Interaction interaction = mock(Interaction.class);
		LocalTransaction localTransaction = mock(LocalTransaction.class);
		final Record record = mock(Record.class);
		final InteractionSpec interactionSpec = mock(InteractionSpec.class);

		given(connectionFactory.getConnection()).willReturn(connection);
		given(connection.getLocalTransaction()).willReturn(localTransaction);
		given(connection.createInteraction()).willReturn(interaction);
		given(interaction.execute(interactionSpec, record, record)).willReturn(true);
		given(connection.getLocalTransaction()).willReturn(localTransaction);

		CciLocalTransactionManager tm = new CciLocalTransactionManager();
		tm.setConnectionFactory(connectionFactory);
		TransactionTemplate tt = new TransactionTemplate(tm);

		tt.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				assertTrue("Has thread connection", TransactionSynchronizationManager.hasResource(connectionFactory));
				CciTemplate ct = new CciTemplate(connectionFactory);
				ct.execute(interactionSpec, record, record);
			}
		});

		verify(localTransaction).begin();
		verify(interaction).close();
		verify(localTransaction).commit();
		verify(connection).close();
	}
