	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		this.sessionHandler = mock(StompSessionHandler.class);
		this.connectHeaders = new StompHeaders();
		this.session = new DefaultStompSession(this.sessionHandler, this.connectHeaders);
		this.session.setMessageConverter(new StringMessageConverter());

		SettableListenableFuture<Void> future = new SettableListenableFuture<>();
		future.set(null);
		when(this.connection.send(this.messageCaptor.capture())).thenReturn(future);
	}
