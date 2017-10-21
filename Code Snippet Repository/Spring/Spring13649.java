	@Test
	public void resolveViewNameAcceptHeaderSortByQuality() throws Exception {
		request.addHeader("Accept", "text/plain;q=0.5, application/json");

		viewResolver.setContentNegotiationManager(new ContentNegotiationManager(new HeaderContentNegotiationStrategy()));

		ViewResolver htmlViewResolver = mock(ViewResolver.class);
		ViewResolver jsonViewResolver = mock(ViewResolver.class);
		viewResolver.setViewResolvers(Arrays.asList(htmlViewResolver, jsonViewResolver));

		View htmlView = mock(View.class, "text_html");
		View jsonViewMock = mock(View.class, "application_json");

		String viewName = "view";
		Locale locale = Locale.ENGLISH;

		given(htmlViewResolver.resolveViewName(viewName, locale)).willReturn(htmlView);
		given(jsonViewResolver.resolveViewName(viewName, locale)).willReturn(jsonViewMock);
		given(htmlView.getContentType()).willReturn("text/html");
		given(jsonViewMock.getContentType()).willReturn("application/json");

		View result = viewResolver.resolveViewName(viewName, locale);
		assertSame("Invalid view", jsonViewMock, result);
	}
