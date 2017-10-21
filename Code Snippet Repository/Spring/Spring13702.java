	@Before
	public void setUp() throws Exception {
		ServletContext sc = new MockServletContext();
		wac = new StaticWebApplicationContext();
		wac.setServletContext(sc);

		// final Template expectedTemplate = new Template();
		fc = new FreeMarkerConfigurer();
		fc.setTemplateLoaderPaths("classpath:/", "file://" + System.getProperty("java.io.tmpdir"));
		fc.setServletContext(sc);
		fc.afterPropertiesSet();

		wac.getDefaultListableBeanFactory().registerSingleton("freeMarkerConfigurer", fc);
		wac.refresh();

		request = new MockHttpServletRequest();
		request.setAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);
		request.setAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE, new AcceptHeaderLocaleResolver());
		request.setAttribute(DispatcherServlet.THEME_RESOLVER_ATTRIBUTE, new FixedThemeResolver());
		response = new MockHttpServletResponse();
	}
