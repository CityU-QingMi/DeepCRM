	@Test
	public void resolveViewContentTypeNull() throws Exception {
		request.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");

		ViewResolver viewResolverMock = mock(ViewResolver.class);
		viewResolver.setViewResolvers(Collections.singletonList(viewResolverMock));

		viewResolver.afterPropertiesSet();

		View viewMock = mock(View.class, "application_xml");

		String viewName = "view";
		Locale locale = Locale.ENGLISH;

		given(viewResolverMock.resolveViewName(viewName, locale)).willReturn(viewMock);
		given(viewMock.getContentType()).willReturn(null);

		View result = viewResolver.resolveViewName(viewName, locale);
		assertNull("Invalid view", result);
	}
