	@Test
	public void testWithConnectionAggregatedStartStop() throws JMSException {
		Connection con = mock(Connection.class);

		SingleConnectionFactory scf = new SingleConnectionFactory(con);
		Connection con1 = scf.createConnection();
		con1.start();
		verify(con).start();
		con1.stop();
		verify(con).stop();
		Connection con2 = scf.createConnection();
		con2.start();
		verify(con, times(2)).start();
		con2.stop();
		verify(con, times(2)).stop();
		con2.start();
		verify(con, times(3)).start();
		con1.start();
		con2.stop();
		con1.stop();
		verify(con, times(3)).stop();
		con1.start();
		verify(con, times(4)).start();
		con1.close();
		verify(con, times(4)).stop();
		con2.close();
		scf.destroy();
		verify(con).close();

		verifyNoMoreInteractions(con);
	}
