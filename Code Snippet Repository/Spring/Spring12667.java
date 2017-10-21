	@Before
	public void setUp() {
		this.context = new StaticWebApplicationContext();
		this.context.setServletContext(new MockServletContext(new FileSystemResourceLoader()));
		this.context.registerSingleton("controller", TestController.class);

		this.config = new TestWebMvcConfigurationSupport();
		this.config.setApplicationContext(this.context);
		this.config.setServletContext(this.context.getServletContext());
	}
