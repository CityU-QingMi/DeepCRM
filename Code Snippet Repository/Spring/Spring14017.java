	@Before
	public void setup() throws Exception {
		logger.debug("Setting up '" + this.testName.getMethodName() + "', client=" +
				this.webSocketClient.getClass().getSimpleName() + ", server=" +
				this.server.getClass().getSimpleName());

		this.wac = new AnnotationConfigWebApplicationContext();
		this.wac.register(getAnnotatedConfigClasses());
		this.wac.register(upgradeStrategyConfigTypes.get(this.server.getClass()));

		if (this.webSocketClient instanceof Lifecycle) {
			((Lifecycle) this.webSocketClient).start();
		}

		this.server.setup();
		this.server.deployConfig(this.wac);
		this.server.start();

		this.wac.setServletContext(this.server.getServletContext());
		this.wac.refresh();
	}
