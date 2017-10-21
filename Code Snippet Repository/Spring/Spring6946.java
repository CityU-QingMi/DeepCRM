	@Test
	public void testTransactionCommitWithMessageProducer() throws JMSException {
		Destination dest = new StubQueue();

		ConnectionFactory cf = mock(ConnectionFactory.class);
		Connection con = mock(Connection.class);
		Session session = mock(Session.class);
		MessageProducer producer = mock(MessageProducer.class);
		final Message message = mock(Message.class);

		given(cf.createConnection()).willReturn(con);
		given(con.createSession(true, Session.AUTO_ACKNOWLEDGE)).willReturn(session);
		given(session.createProducer(dest)).willReturn(producer);
		given(session.getTransacted()).willReturn(true);

		JmsTransactionManager tm = new JmsTransactionManager(cf);
		TransactionStatus ts = tm.getTransaction(new DefaultTransactionDefinition());
		JmsTemplate jt = new JmsTemplate(cf);
		jt.send(dest, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return message;
			}
		});
		tm.commit(ts);

		verify(producer).send(message);
		verify(session).commit();
		verify(producer).close();
		verify(session).close();
		verify(con).close();
	}
