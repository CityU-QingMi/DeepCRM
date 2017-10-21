	@Test
	public void testWithConnectionFactoryAndLocalExceptionListenerWithCleanup() throws JMSException {
		ConnectionFactory cf = mock(ConnectionFactory.class);
		TestConnection con = new TestConnection();
		given(cf.createConnection()).willReturn(con);

		TestExceptionListener listener0 = new TestExceptionListener();
		TestExceptionListener listener1 = new TestExceptionListener();
		TestExceptionListener listener2 = new TestExceptionListener();

		SingleConnectionFactory scf = new SingleConnectionFactory(cf) {
			@Override
			public void onException(JMSException ex) {
				// no-op
			}
		};
		scf.setReconnectOnException(true);
		scf.setExceptionListener(listener0);
		Connection con1 = scf.createConnection();
		con1.setExceptionListener(listener1);
		assertSame(listener1, con1.getExceptionListener());
		Connection con2 = scf.createConnection();
		con2.setExceptionListener(listener2);
		assertSame(listener2, con2.getExceptionListener());
		con.getExceptionListener().onException(new JMSException(""));
		con2.close();
		con.getExceptionListener().onException(new JMSException(""));
		con1.close();
		con.getExceptionListener().onException(new JMSException(""));
		scf.destroy();  // should trigger actual close

		assertEquals(0, con.getStartCount());
		assertEquals(1, con.getCloseCount());
		assertEquals(3, listener0.getCount());
		assertEquals(2, listener1.getCount());
		assertEquals(1, listener2.getCount());
	}
