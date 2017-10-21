	@Before
	public void setup() {
		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		Properties props = new Properties();
		props.setProperty("myOrigin", "http://example.com");
		wac.getEnvironment().getPropertySources().addFirst(new PropertiesPropertySource("ps", props));
		wac.registerSingleton("ppc", PropertySourcesPlaceholderConfigurer.class);
		wac.refresh();

		this.handlerMapping.setRemoveSemicolonContent(false);
		wac.getAutowireCapableBeanFactory().initializeBean(this.handlerMapping, "hm");

		this.request.setMethod("GET");
		this.request.addHeader(HttpHeaders.ORIGIN, "http://domain.com/");
	}
