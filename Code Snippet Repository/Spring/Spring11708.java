	@Override
	protected ApplicationContext initApplicationContext() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(WebConfig.class);
		Properties props = new Properties();
		props.setProperty("myOrigin", "http://site1.com");
		context.getEnvironment().getPropertySources().addFirst(new PropertiesPropertySource("ps", props));
		context.register(PropertySourcesPlaceholderConfigurer.class);
		context.refresh();
		return context;
	}
