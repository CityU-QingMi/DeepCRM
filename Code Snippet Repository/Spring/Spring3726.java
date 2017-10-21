	@Test
	public void testPlaceholderBased() throws Exception {
		MockEnvironment env = new MockEnvironment();
		env.setProperty("serverName", "server");
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.setEnvironment(env);
		context.register(PlaceholderBasedConfiguration.class);
		context.refresh();
		this.ctx = context;
		validateAnnotationTestBean();
	}
