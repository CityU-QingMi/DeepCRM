	@Before
	public void setupMocks() throws Exception {
		this.jndiContext = mock(Context.class);
		this.connectionFactory = mock(ConnectionFactory.class);
		this.connection = mock(Connection.class);
		this.session = mock(Session.class);
		this.queue = mock(Queue.class);

		given(this.connectionFactory.createConnection()).willReturn(this.connection);
		given(this.connection.createSession(useTransactedTemplate(), Session.AUTO_ACKNOWLEDGE)).willReturn(this.session);
		given(this.session.getTransacted()).willReturn(useTransactedSession());
		given(this.jndiContext.lookup("testDestination")).willReturn(this.queue);
	}
