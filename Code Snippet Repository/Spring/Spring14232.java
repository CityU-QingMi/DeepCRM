	@Before
	public void setup() {
		super.setUp();
		MockitoAnnotations.initMocks(this);

		Map<String, Object> attributes = Collections.emptyMap();
		this.session = new TestSockJsSession(sessionId, new StubSockJsServiceConfig(), this.wsHandler, attributes);

		given(this.xhrHandler.getTransportType()).willReturn(TransportType.XHR);
		given(this.xhrHandler.createSession(sessionId, this.wsHandler, attributes)).willReturn(this.session);
		given(this.xhrSendHandler.getTransportType()).willReturn(TransportType.XHR_SEND);
		given(this.jsonpHandler.getTransportType()).willReturn(TransportType.JSONP);
		given(this.jsonpHandler.createSession(sessionId, this.wsHandler, attributes)).willReturn(this.session);
		given(this.jsonpSendHandler.getTransportType()).willReturn(TransportType.JSONP_SEND);
		given(this.wsTransportHandler.getTransportType()).willReturn(TransportType.WEBSOCKET);

		this.service = new TransportHandlingSockJsService(this.taskScheduler, this.xhrHandler, this.xhrSendHandler);
	}
