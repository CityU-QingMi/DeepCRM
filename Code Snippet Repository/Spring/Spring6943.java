	@Test
	public void testParticipatingTransactionWithRollbackOnly() throws JMSException {
		ConnectionFactory cf = mock(ConnectionFactory.class);
		Connection con = mock(Connection.class);
		final Session session = mock(Session.class);

		given(cf.createConnection()).willReturn(con);
		given(con.createSession(true, Session.AUTO_ACKNOWLEDGE)).willReturn(session);

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
		tt.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				jt.execute(new SessionCallback<Void>() {
					@Override
					public Void doInJms(Session sess) {
						assertTrue(sess == session);
						return null;
					}
				});
				status.setRollbackOnly();
			}
		});
		try {
			tm.commit(ts);
			fail("Should have thrown UnexpectedRollbackException");
		}
		catch (UnexpectedRollbackException ex) {
			// expected
		}

		verify(session).rollback();
		verify(session).close();
		verify(con).close();
	}
