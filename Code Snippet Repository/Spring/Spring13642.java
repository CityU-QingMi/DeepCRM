	@Test
	public void resolveViewNoMatchUseUnacceptableStatus() throws Exception {
		viewResolver.setUseNotAcceptableStatusCode(true);
		request.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9");

		ViewResolver viewResolverMock = mock(ViewResolver.class);
		viewResolver.setViewResolvers(Collections.singletonList(viewResolverMock));

		viewResolver.afterPropertiesSet();

		View viewMock = mock(View.class, "application_xml");

		String viewName = "view";
		Locale locale = Locale.ENGLISH;

		given(viewResolverMock.resolveViewName(viewName, locale)).willReturn(viewMock);
		given(viewMock.getContentType()).willReturn("application/pdf");

		View result = viewResolver.resolveViewName(viewName, locale);
		assertNotNull("Invalid view", result);
		MockHttpServletResponse response = new MockHttpServletResponse();
		result.render(null, request, response);
		assertEquals("Invalid status code set", 406, response.getStatus());
	}
