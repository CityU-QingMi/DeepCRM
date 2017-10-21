	@Before
	public void createViewResolver() {
		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		wac.setServletContext(new MockServletContext());
		wac.refresh();
		viewResolver = new ContentNegotiatingViewResolver();
		viewResolver.setApplicationContext(wac);
		request = new MockHttpServletRequest("GET", "/test");
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	}
