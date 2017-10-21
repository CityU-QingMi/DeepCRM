	@Before
	public void setUp() throws Exception {
		when(this.store.createWebSession()).thenReturn(Mono.just(this.createSession));
		when(this.createSession.save()).thenReturn(Mono.empty());
		when(this.updateSession.getId()).thenReturn("update-session-id");

		this.manager = new DefaultWebSessionManager();
		this.manager.setSessionIdResolver(this.idResolver);
		this.manager.setSessionStore(this.store);

		MockServerHttpRequest request = MockServerHttpRequest.get("/path").build();
		MockServerHttpResponse response = new MockServerHttpResponse();
		this.exchange = new DefaultServerWebExchange(request, response, this.manager,
				ServerCodecConfigurer.create(), new AcceptHeaderLocaleContextResolver());
	}
