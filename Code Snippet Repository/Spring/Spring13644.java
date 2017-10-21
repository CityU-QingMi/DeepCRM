	@Test
	public void resolveViewNameWithPathExtension() throws Exception {
		request.setRequestURI("/test.xls");

		ViewResolver viewResolverMock = mock(ViewResolver.class);
		viewResolver.setViewResolvers(Collections.singletonList(viewResolverMock));
		viewResolver.afterPropertiesSet();

		View viewMock = mock(View.class, "application_xls");

		String viewName = "view";
		Locale locale = Locale.ENGLISH;

		given(viewResolverMock.resolveViewName(viewName, locale)).willReturn(null);
		given(viewResolverMock.resolveViewName(viewName + ".xls", locale)).willReturn(viewMock);
		given(viewMock.getContentType()).willReturn("application/vnd.ms-excel");

		View result = viewResolver.resolveViewName(viewName, locale);
		assertSame("Invalid view", viewMock, result);
	}
