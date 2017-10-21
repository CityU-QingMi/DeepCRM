	@Test
	public void testWithQueueConnectionFactoryAndJms102Usage() throws JMSException {
		QueueConnectionFactory cf = mock(QueueConnectionFactory.class);
		QueueConnection con = mock(QueueConnection.class);

		given(cf.createQueueConnection()).willReturn(con);

		SingleConnectionFactory scf = new SingleConnectionFactory(cf);
		Connection con1 = scf.createQueueConnection();
		Connection con2 = scf.createQueueConnection();
		con1.start();
		con2.start();
		con1.close();
		con2.close();
		scf.destroy();  // should trigger actual close

		verify(con).start();
		verify(con).stop();
		verify(con).close();
		verifyNoMoreInteractions(con);
	}
