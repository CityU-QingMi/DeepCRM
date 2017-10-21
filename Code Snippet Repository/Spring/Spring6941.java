	@Test
	public void testTransactionRollback() throws JMSException {
		ConnectionFactory cf = mock(ConnectionFactory.class);
		Connection con = mock(Connection.class);
		final Session session = mock(Session.class);

		given(cf.createConnection()).willReturn(con);
		given(con.createSession(true, Session.AUTO_ACKNOWLEDGE)).willReturn(session);

		JmsTransactionManager tm = new JmsTransactionManager(cf);
		TransactionStatus ts = tm.getTransaction(new DefaultTransactionDefinition());
		JmsTemplate jt = new JmsTemplate(cf);
		jt.execute(new SessionCallback<Void>() {
			@Override
			public Void doInJms(Session sess) {
				assertTrue(sess == session);
				return null;
			}
		});
		tm.rollback(ts);

		verify(session).rollback();
		verify(session).close();
		verify(con).close();
	}
