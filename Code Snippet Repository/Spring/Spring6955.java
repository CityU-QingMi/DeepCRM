	@Test
	public void testCachingConnectionFactoryWithQueueConnectionFactoryAndJms102Usage() throws JMSException {
		QueueConnectionFactory cf = mock(QueueConnectionFactory.class);
		QueueConnection con = mock(QueueConnection.class);
		QueueSession txSession = mock(QueueSession.class);
		QueueSession nonTxSession = mock(QueueSession.class);

		given(cf.createQueueConnection()).willReturn(con);
		given(con.createQueueSession(true, Session.AUTO_ACKNOWLEDGE)).willReturn(txSession);
		given(txSession.getTransacted()).willReturn(true);
		given(con.createQueueSession(false, Session.CLIENT_ACKNOWLEDGE)).willReturn(nonTxSession);

		CachingConnectionFactory scf = new CachingConnectionFactory(cf);
		scf.setReconnectOnException(false);
		Connection con1 = scf.createQueueConnection();
		Session session1 = con1.createSession(true, Session.AUTO_ACKNOWLEDGE);
		session1.rollback();
		session1.close();
		session1 = con1.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		session1.close();
		con1.start();
		QueueConnection con2 = scf.createQueueConnection();
		Session session2 = con2.createQueueSession(false, Session.CLIENT_ACKNOWLEDGE);
		session2.close();
		session2 = con2.createSession(true, Session.AUTO_ACKNOWLEDGE);
		session2.getTransacted();
		session2.close();  // should lead to rollback
		con2.start();
		con1.close();
		con2.close();
		scf.destroy();  // should trigger actual close

		verify(txSession).rollback();
		verify(txSession).close();
		verify(nonTxSession).close();
		verify(con).start();
		verify(con).stop();
		verify(con).close();
	}
