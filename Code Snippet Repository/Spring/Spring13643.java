	@Test
	public void nestedViewResolverIsNotSpringBean() throws Exception {
		StaticWebApplicationContext webAppContext = new StaticWebApplicationContext();
		webAppContext.setServletContext(new MockServletContext());
		webAppContext.refresh();

		InternalResourceViewResolver nestedResolver = new InternalResourceViewResolver();
		nestedResolver.setApplicationContext(webAppContext);
		nestedResolver.setViewClass(InternalResourceView.class);
		viewResolver.setViewResolvers(new ArrayList<>(Arrays.asList(nestedResolver)));

		FixedContentNegotiationStrategy fixedStrategy = new FixedContentNegotiationStrategy(MediaType.TEXT_HTML);
		viewResolver.setContentNegotiationManager(new ContentNegotiationManager(fixedStrategy));

		viewResolver.afterPropertiesSet();

		String viewName = "view";
		Locale locale = Locale.ENGLISH;

		View result = viewResolver.resolveViewName(viewName, locale);
		assertNotNull("Invalid view", result);
	}
