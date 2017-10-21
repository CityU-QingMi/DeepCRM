	@Test
	public void resolveViewNameWithAcceptHeader() throws Exception {
		request.addHeader("Accept", "application/vnd.ms-excel");

		Map<String, MediaType> mapping = Collections.singletonMap("xls", MediaType.valueOf("application/vnd.ms-excel"));
		MappingMediaTypeFileExtensionResolver extensionsResolver = new MappingMediaTypeFileExtensionResolver(mapping);
		ContentNegotiationManager manager = new ContentNegotiationManager(new HeaderContentNegotiationStrategy());
		manager.addFileExtensionResolvers(extensionsResolver);
		viewResolver.setContentNegotiationManager(manager);

		ViewResolver viewResolverMock = mock(ViewResolver.class);
		viewResolver.setViewResolvers(Collections.singletonList(viewResolverMock));

		View viewMock = mock(View.class, "application_xls");

		String viewName = "view";
		Locale locale = Locale.ENGLISH;

		given(viewResolverMock.resolveViewName(viewName, locale)).willReturn(null);
		given(viewResolverMock.resolveViewName(viewName + ".xls", locale)).willReturn(viewMock);
		given(viewMock.getContentType()).willReturn("application/vnd.ms-excel");

		View result = viewResolver.resolveViewName(viewName, locale);
		assertSame("Invalid view", viewMock, result);
	}
