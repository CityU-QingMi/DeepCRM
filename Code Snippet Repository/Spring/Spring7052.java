	@Before
	public void setUpMocks() throws Exception {
		mockConnectionFactory = mock(QueueConnectionFactory.class);
		mockConnection = mock(QueueConnection.class);
		mockSession = mock(QueueSession.class);
		mockQueue = mock(Queue.class);

		given(mockConnectionFactory.createConnection()).willReturn(mockConnection);
		given(mockConnection.createSession(false, Session.AUTO_ACKNOWLEDGE)).willReturn(mockSession);
	}
