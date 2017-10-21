	@Test
	public void resolveViewNameWithRequestParameter() throws Exception {
		request.addParameter("format", "xls");

		Map<String, MediaType> mapping = Collections.singletonMap("xls", MediaType.valueOf("application/vnd.ms-excel"));
		ParameterContentNegotiationStrategy paramStrategy = new ParameterContentNegotiationStrategy(mapping);
		viewResolver.setContentNegotiationManager(new ContentNegotiationManager(paramStrategy));

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
