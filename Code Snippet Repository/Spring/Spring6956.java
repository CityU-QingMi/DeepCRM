	@Test
	public void testCachingConnectionFactoryWithTopicConnectionFactoryAndJms102Usage() throws JMSException {
		TopicConnectionFactory cf = mock(TopicConnectionFactory.class);
		TopicConnection con = mock(TopicConnection.class);
		TopicSession txSession = mock(TopicSession.class);
		TopicSession nonTxSession = mock(TopicSession.class);

		given(cf.createTopicConnection()).willReturn(con);
		given(con.createTopicSession(true, Session.AUTO_ACKNOWLEDGE)).willReturn(txSession);
		given(txSession.getTransacted()).willReturn(true);
		given(con.createTopicSession(false, Session.CLIENT_ACKNOWLEDGE)).willReturn(nonTxSession);

		CachingConnectionFactory scf = new CachingConnectionFactory(cf);
		scf.setReconnectOnException(false);
		Connection con1 = scf.createTopicConnection();
		Session session1 = con1.createSession(true, Session.AUTO_ACKNOWLEDGE);
		session1.getTransacted();
		session1.close();  // should lead to rollback
		session1 = con1.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		session1.close();
		con1.start();
		TopicConnection con2 = scf.createTopicConnection();
		Session session2 = con2.createTopicSession(false, Session.CLIENT_ACKNOWLEDGE);
		session2.close();
		session2 = con2.createSession(true, Session.AUTO_ACKNOWLEDGE);
		session2.getTransacted();
		session2.close();
		con2.start();
		con1.close();
		con2.close();
		scf.destroy();  // should trigger actual close

		verify(txSession).close();
		verify(nonTxSession).close();
		verify(con).start();
		verify(con).stop();
		verify(con).close();
	}
