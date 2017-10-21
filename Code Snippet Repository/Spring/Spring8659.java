	@Before
	public void setUp() throws Exception {

		HttpHandler httpHandler = RouterFunctions.toHttpHandler(
				route(GET("/test"), request ->
						ServerResponse.ok().syncBody("It works!")));

		this.server = new ReactorHttpServer();
		this.server.setHandler(httpHandler);
		this.server.afterPropertiesSet();
		this.server.start();

		this.client = WebTestClient.bindToServer()
				.baseUrl("http://localhost:" + this.server.getPort())
				.build();
	}
