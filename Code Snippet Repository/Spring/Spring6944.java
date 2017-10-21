	@Test
	public void testSuspendedTransaction() throws JMSException {
		final ConnectionFactory cf = mock(ConnectionFactory.class);
		Connection con = mock(Connection.class);
		final Session session = mock(Session.class);
		final Session session2 = mock(Session.class);

		given(cf.createConnection()).willReturn(con);
		given(con.createSession(true, Session.AUTO_ACKNOWLEDGE)).willReturn(session);
		given(con.createSession(false, Session.AUTO_ACKNOWLEDGE)).willReturn(session2);

		JmsTransactionManager tm = new JmsTransactionManager(cf);
		TransactionStatus ts = tm.getTransaction(new DefaultTransactionDefinition());
		final JmsTemplate jt = new JmsTemplate(cf);
		jt.execute(new SessionCallback<Void>() {
			@Override
			public Void doInJms(Session sess) {
				assertTrue(sess == session);
				return null;
			}
		});
		TransactionTemplate tt = new TransactionTemplate(tm);
		tt.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
		tt.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				jt.execute(new SessionCallback<Void>() {
					@Override
					public Void doInJms(Session sess) {
						assertTrue(sess != session);
						return null;
					}
				});
			}
		});
		jt.execute(new SessionCallback<Void>() {
			@Override
			public Void doInJms(Session sess) {
				assertTrue(sess == session);
				return null;
			}
		});
		tm.commit(ts);

		verify(session).commit();
		verify(session).close();
		verify(session2).close();
		verify(con, times(2)).close();
	}
