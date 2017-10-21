	@Test
	public void testWithQueueConnection() throws JMSException {
		Connection con = mock(QueueConnection.class);

		SingleConnectionFactory scf = new SingleConnectionFactory(con);
		QueueConnection con1 = scf.createQueueConnection();
		con1.start();
		con1.stop();
		con1.close();
		QueueConnection con2 = scf.createQueueConnection();
		con2.start();
		con2.stop();
		con2.close();
		scf.destroy();  // should trigger actual close

		verify(con, times(2)).start();
		verify(con, times(2)).stop();
		verify(con).close();
		verifyNoMoreInteractions(con);
	}
