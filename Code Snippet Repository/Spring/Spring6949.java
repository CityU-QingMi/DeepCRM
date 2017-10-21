	@Test
	public void testWithConnectionFactoryAndExceptionListener() throws JMSException {
		ConnectionFactory cf = mock(ConnectionFactory.class);
		Connection con = mock(Connection.class);

		ExceptionListener listener = new ChainedExceptionListener();
		given(cf.createConnection()).willReturn(con);
		given(con.getExceptionListener()).willReturn(listener);

		SingleConnectionFactory scf = new SingleConnectionFactory(cf);
		scf.setExceptionListener(listener);
		Connection con1 = scf.createConnection();
		assertEquals(listener, con1.getExceptionListener());
		con1.start();
		con1.stop();
		con1.close();
		Connection con2 = scf.createConnection();
		con2.start();
		con2.stop();
		con2.close();
		scf.destroy();  // should trigger actual close

		verify(con).setExceptionListener(listener);
		verify(con, times(2)).start();
		verify(con, times(2)).stop();
		verify(con).close();
	}
