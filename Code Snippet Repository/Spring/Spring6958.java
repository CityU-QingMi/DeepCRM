	@Test
	public void testWithTopicConnection() throws JMSException {
		Connection con = mock(TopicConnection.class);

		SingleConnectionFactory scf = new SingleConnectionFactory(con);
		TopicConnection con1 = scf.createTopicConnection();
		con1.start();
		con1.stop();
		con1.close();
		TopicConnection con2 = scf.createTopicConnection();
		con2.start();
		con2.stop();
		con2.close();
		scf.destroy();  // should trigger actual close

		verify(con, times(2)).start();
		verify(con, times(2)).stop();
		verify(con).close();
		verifyNoMoreInteractions(con);
	}
