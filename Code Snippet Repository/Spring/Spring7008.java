	@Test
	public void testContextRefreshedEventDoesNotStartTheConnectionIfAutoStartIsSetToFalse() throws Exception {
		MessageConsumer messageConsumer = mock(MessageConsumer.class);
		Session session = mock(Session.class);
		// Queue gets created in order to create MessageConsumer for that Destination...
		given(session.createQueue(DESTINATION_NAME)).willReturn(QUEUE_DESTINATION);
		// and then the MessageConsumer gets created...
		given(session.createConsumer(QUEUE_DESTINATION, null)).willReturn(messageConsumer); // no MessageSelector...

		Connection connection = mock(Connection.class);
		// session gets created in order to register MessageListener...
		given(connection.createSession(this.container.isSessionTransacted(),
				this.container.getSessionAcknowledgeMode())).willReturn(session);

		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		given(connectionFactory.createConnection()).willReturn(connection);

		this.container.setConnectionFactory(connectionFactory);
		this.container.setDestinationName(DESTINATION_NAME);

		this.container.setMessageListener(new TestMessageListener());
		this.container.setAutoStartup(false);
		this.container.afterPropertiesSet();
		GenericApplicationContext context = new GenericApplicationContext();
		context.getBeanFactory().registerSingleton("messageListenerContainer", this.container);
		context.refresh();

		verify(connection).setExceptionListener(this.container);
	}
