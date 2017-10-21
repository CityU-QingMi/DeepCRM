	@Test
	public void testWithTopicConnectionFactoryAndJms102Usage() throws JMSException {
		TopicConnectionFactory cf = mock(TopicConnectionFactory.class);
		TopicConnection con = mock(TopicConnection.class);

		given(cf.createTopicConnection()).willReturn(con);

		SingleConnectionFactory scf = new SingleConnectionFactory(cf);
		Connection con1 = scf.createTopicConnection();
		Connection con2 = scf.createTopicConnection();
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
