	@Test
	public void testWithQueueConnectionFactoryAndJms11Usage() throws JMSException {
		QueueConnectionFactory cf = mock(QueueConnectionFactory.class);
		QueueConnection con = mock(QueueConnection.class);

		given(cf.createConnection()).willReturn(con);

		SingleConnectionFactory scf = new SingleConnectionFactory(cf);
		Connection con1 = scf.createConnection();
		Connection con2 = scf.createConnection();
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
