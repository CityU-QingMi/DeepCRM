	@Before
	public void setup() {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setCookieDomain("domain");
		localeResolver.setCookieHttpOnly(true);

		this.mockMvc = standaloneSetup(new SimpleController())
				.addInterceptors(new LocaleChangeInterceptor())
				.setLocaleResolver(localeResolver)
				.defaultRequest(get("/").param("locale", "en_US"))
				.alwaysExpect(status().isOk())
				.build();
	}
