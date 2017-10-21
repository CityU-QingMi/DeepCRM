	@Test
	public void testWithConnection() throws JMSException {
		Connection con = mock(Connection.class);

		SingleConnectionFactory scf = new SingleConnectionFactory(con);
		Connection con1 = scf.createConnection();
		con1.start();
		con1.stop();
		con1.close();
		Connection con2 = scf.createConnection();
		con2.start();
		con2.stop();
		con2.close();
		scf.destroy();  // should trigger actual close

		verify(con, times(2)).start();
		verify(con, times(2)).stop();
		verify(con).close();
		verifyNoMoreInteractions(con);
	}
