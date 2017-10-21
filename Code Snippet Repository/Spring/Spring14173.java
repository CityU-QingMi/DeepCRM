	@Before
	public void setup() {
		this.servletContext = new MockServletContext();

		this.webAppContext = new AnnotationConfigWebApplicationContext();
		this.webAppContext.register(Config.class);

		this.contextLoader = new ContextLoader(this.webAppContext);
		this.contextLoader.initWebApplicationContext(this.servletContext);

		this.configurator = new SpringConfigurator();
	}
