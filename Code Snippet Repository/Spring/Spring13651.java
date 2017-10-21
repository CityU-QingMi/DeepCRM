	@Test
	public void resolveViewNameAcceptHeaderDefaultView() throws Exception {
		request.addHeader("Accept", "application/json");

		ViewResolver viewResolverMock1 = mock(ViewResolver.class);
		ViewResolver viewResolverMock2 = mock(ViewResolver.class);
		viewResolver.setViewResolvers(Arrays.asList(viewResolverMock1, viewResolverMock2));

		View viewMock1 = mock(View.class, "application_xml");
		View viewMock2 = mock(View.class, "text_html");
		View viewMock3 = mock(View.class, "application_json");

		List<View> defaultViews = new ArrayList<>();
		defaultViews.add(viewMock3);
		viewResolver.setDefaultViews(defaultViews);

		viewResolver.afterPropertiesSet();

		String viewName = "view";
		Locale locale = Locale.ENGLISH;

		given(viewResolverMock1.resolveViewName(viewName, locale)).willReturn(viewMock1);
		given(viewResolverMock2.resolveViewName(viewName, locale)).willReturn(viewMock2);
		given(viewMock1.getContentType()).willReturn("application/xml");
		given(viewMock2.getContentType()).willReturn("text/html;charset=ISO-8859-1");
		given(viewMock3.getContentType()).willReturn("application/json");

		View result = viewResolver.resolveViewName(viewName, locale);
		assertSame("Invalid view", viewMock3, result);
	}
