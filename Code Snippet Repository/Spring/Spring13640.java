	@Test
	public void resolveViewNameRedirectView() throws Exception {
		request.addHeader("Accept", "application/json");
		request.setRequestURI("/test");

		StaticWebApplicationContext webAppContext = new StaticWebApplicationContext();
		webAppContext.setServletContext(new MockServletContext());
		webAppContext.refresh();

		UrlBasedViewResolver urlViewResolver = new InternalResourceViewResolver();
		urlViewResolver.setApplicationContext(webAppContext);
		ViewResolver xmlViewResolver = mock(ViewResolver.class);
		viewResolver.setViewResolvers(Arrays.<ViewResolver>asList(xmlViewResolver, urlViewResolver));

		View xmlView = mock(View.class, "application_xml");
		View jsonView = mock(View.class, "application_json");
		viewResolver.setDefaultViews(Arrays.asList(jsonView));

		viewResolver.afterPropertiesSet();

		String viewName = "redirect:anotherTest";
		Locale locale = Locale.ENGLISH;

		given(xmlViewResolver.resolveViewName(viewName, locale)).willReturn(xmlView);
		given(jsonView.getContentType()).willReturn("application/json");

		View actualView = viewResolver.resolveViewName(viewName, locale);
		assertEquals("Invalid view", RedirectView.class, actualView.getClass());
	}
