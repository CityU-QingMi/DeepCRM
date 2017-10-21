	@Before
	public void setup() throws Exception {
		logger.debug("Setting up '" + this.testName.getMethodName() + "'");
		this.testFilter = new TestFilter();

		this.wac = new AnnotationConfigWebApplicationContext();
		this.wac.register(TestConfig.class, upgradeStrategyConfigClass());

		this.server = createWebSocketTestServer();
		this.server.setup();
		this.server.deployConfig(this.wac, this.testFilter);
		this.server.start();

		this.wac.setServletContext(this.server.getServletContext());
		this.wac.refresh();

		this.baseUrl = "http://localhost:" + this.server.getPort();
	}
