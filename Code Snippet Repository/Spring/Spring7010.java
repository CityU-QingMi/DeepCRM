	@Test
	public void testTaskExecutorCorrectlyInvokedWhenSpecified() throws Exception {
		final SimpleMessageConsumer messageConsumer = new SimpleMessageConsumer();

		final Session session = mock(Session.class);
		given(session.createQueue(DESTINATION_NAME)).willReturn(QUEUE_DESTINATION);
		given(session.createConsumer(QUEUE_DESTINATION, null)).willReturn(messageConsumer); // no MessageSelector...
		given(session.getTransacted()).willReturn(false);
		given(session.getAcknowledgeMode()).willReturn(Session.AUTO_ACKNOWLEDGE);

		Connection connection = mock(Connection.class);
		given(connection.createSession(this.container.isSessionTransacted(),
				this.container.getSessionAcknowledgeMode())).willReturn(session);

		final ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		given(connectionFactory.createConnection()).willReturn(connection);

		final TestMessageListener listener = new TestMessageListener();

		this.container.setConnectionFactory(connectionFactory);
		this.container.setDestinationName(DESTINATION_NAME);
		this.container.setMessageListener(listener);
		this.container.setTaskExecutor(new TaskExecutor() {
			@Override
			public void execute(Runnable task) {
				listener.executorInvoked = true;
				assertFalse(listener.listenerInvoked);
				task.run();
				assertTrue(listener.listenerInvoked);
			}
		});
		this.container.afterPropertiesSet();
		this.container.start();

		final Message message = mock(Message.class);
		messageConsumer.sendMessage(message);

		assertTrue(listener.executorInvoked);
		assertTrue(listener.listenerInvoked);

		verify(connection).setExceptionListener(this.container);
		verify(connection).start();
	}
