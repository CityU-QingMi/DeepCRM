	@Test
	public void resolveViewNameWithDefaultContentType() throws Exception {
		request.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");

		MediaType mediaType = new MediaType("application", "xml");
		FixedContentNegotiationStrategy fixedStrategy = new FixedContentNegotiationStrategy(mediaType);
		viewResolver.setContentNegotiationManager(new ContentNegotiationManager(fixedStrategy));

		ViewResolver viewResolverMock1 = mock(ViewResolver.class, "viewResolver1");
		ViewResolver viewResolverMock2 = mock(ViewResolver.class, "viewResolver2");
		viewResolver.setViewResolvers(Arrays.asList(viewResolverMock1, viewResolverMock2));
		viewResolver.afterPropertiesSet();

		View viewMock1 = mock(View.class, "application_xml");
		View viewMock2 = mock(View.class, "text_html");

		String viewName = "view";
		Locale locale = Locale.ENGLISH;

		given(viewResolverMock1.resolveViewName(viewName, locale)).willReturn(viewMock1);
		given(viewResolverMock2.resolveViewName(viewName, locale)).willReturn(viewMock2);
		given(viewMock1.getContentType()).willReturn("application/xml");
		given(viewMock2.getContentType()).willReturn("text/html;charset=ISO-8859-1");

		View result = viewResolver.resolveViewName(viewName, locale);
		assertSame("Invalid view", viewMock1, result);
	}
