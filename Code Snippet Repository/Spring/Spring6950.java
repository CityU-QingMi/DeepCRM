	@Test
	public void testWithConnectionFactoryAndReconnectOnException() throws JMSException {
		ConnectionFactory cf = mock(ConnectionFactory.class);
		TestConnection con = new TestConnection();
		given(cf.createConnection()).willReturn(con);

		SingleConnectionFactory scf = new SingleConnectionFactory(cf);
		scf.setReconnectOnException(true);
		Connection con1 = scf.createConnection();
		assertNull(con1.getExceptionListener());
		con1.start();
		con.getExceptionListener().onException(new JMSException(""));
		Connection con2 = scf.createConnection();
		con2.start();
		scf.destroy();  // should trigger actual close

		assertEquals(2, con.getStartCount());
		assertEquals(2, con.getCloseCount());
	}
