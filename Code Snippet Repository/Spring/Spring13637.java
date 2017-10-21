	@Test
	public void resolveViewNameFilename() throws Exception {
		request.setRequestURI("/test.html");

		ViewResolver viewResolverMock1 = mock(ViewResolver.class, "viewResolver1");
		ViewResolver viewResolverMock2 = mock(ViewResolver.class, "viewResolver2");
		viewResolver.setViewResolvers(Arrays.asList(viewResolverMock1, viewResolverMock2));

		viewResolver.afterPropertiesSet();

		View viewMock1 = mock(View.class, "application_xml");
		View viewMock2 = mock(View.class, "text_html");

		String viewName = "view";
		Locale locale = Locale.ENGLISH;

		given(viewResolverMock1.resolveViewName(viewName, locale)).willReturn(viewMock1);
		given(viewResolverMock1.resolveViewName(viewName + ".html", locale)).willReturn(null);
		given(viewResolverMock2.resolveViewName(viewName, locale)).willReturn(null);
		given(viewResolverMock2.resolveViewName(viewName + ".html", locale)).willReturn(viewMock2);
		given(viewMock1.getContentType()).willReturn("application/xml");
		given(viewMock2.getContentType()).willReturn("text/html;charset=ISO-8859-1");

		View result = viewResolver.resolveViewName(viewName, locale);
		assertSame("Invalid view", viewMock2, result);
	}
