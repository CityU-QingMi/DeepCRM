	@Test
	public void testWithConnectionFactoryAndExceptionListenerAndReconnectOnException() throws JMSException {
		ConnectionFactory cf = mock(ConnectionFactory.class);
		TestConnection con = new TestConnection();
		given(cf.createConnection()).willReturn(con);

		TestExceptionListener listener = new TestExceptionListener();
		SingleConnectionFactory scf = new SingleConnectionFactory(cf);
		scf.setExceptionListener(listener);
		scf.setReconnectOnException(true);
		Connection con1 = scf.createConnection();
		assertSame(listener, con1.getExceptionListener());
		con1.start();
		con.getExceptionListener().onException(new JMSException(""));
		Connection con2 = scf.createConnection();
		con2.start();
		scf.destroy();  // should trigger actual close

		assertEquals(2, con.getStartCount());
		assertEquals(2, con.getCloseCount());
		assertEquals(1, listener.getCount());
	}
