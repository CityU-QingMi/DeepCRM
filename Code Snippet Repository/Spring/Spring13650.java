	@Test
	public void resolveViewNameAcceptHeaderWithSuffix() throws Exception {
		request.addHeader("Accept", "application/vnd.example-v2+xml");

		ViewResolver viewResolverMock = mock(ViewResolver.class);
		viewResolver.setViewResolvers(Arrays.asList(viewResolverMock));

		viewResolver.afterPropertiesSet();

		View viewMock = mock(View.class, "application_xml");

		String viewName = "view";
		Locale locale = Locale.ENGLISH;

		given(viewResolverMock.resolveViewName(viewName, locale)).willReturn(viewMock);
		given(viewMock.getContentType()).willReturn("application/*+xml");

		View result = viewResolver.resolveViewName(viewName, locale);

		assertSame("Invalid view", viewMock, result);
		assertEquals(new MediaType("application", "vnd.example-v2+xml"), request.getAttribute(View.SELECTED_CONTENT_TYPE));
	}
