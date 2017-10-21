	@Before
	public void setup() throws Exception {
		config.setAllowedOrigins(Arrays.asList("http://domain1.com", "http://domain2.com"));
		config.setAllowedMethods(Arrays.asList("GET", "POST"));
		config.setAllowedHeaders(Arrays.asList("header1", "header2"));
		config.setExposedHeaders(Arrays.asList("header3", "header4"));
		config.setMaxAge(123L);
		config.setAllowCredentials(false);
		filter = new CorsWebFilter(r -> config);
	}
