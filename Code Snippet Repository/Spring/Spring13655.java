	@Test
	public void includeOnCommitted() throws Exception {
		given(request.getAttribute(View.PATH_VARIABLES)).willReturn(null);
		given(request.getAttribute(WebUtils.INCLUDE_REQUEST_URI_ATTRIBUTE)).willReturn(null);
		given(request.getRequestDispatcher(url)).willReturn(new MockRequestDispatcher(url));

		response.setCommitted(true);
		view.setUrl(url);

		// Can now try multiple tests
		view.render(model, request, response);
		assertEquals(url, response.getIncludedUrl());

		model.forEach((k, v) -> verify(request).setAttribute(k, v));
	}
