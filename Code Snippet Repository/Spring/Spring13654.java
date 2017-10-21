	@Test
	public void includeOnAttribute() throws Exception {
		given(request.getAttribute(View.PATH_VARIABLES)).willReturn(null);
		given(request.getAttribute(WebUtils.INCLUDE_REQUEST_URI_ATTRIBUTE)).willReturn("somepath");
		given(request.getRequestDispatcher(url)).willReturn(new MockRequestDispatcher(url));

		view.setUrl(url);

		// Can now try multiple tests
		view.render(model, request, response);
		assertEquals(url, response.getIncludedUrl());

		model.forEach((key, value) -> verify(request).setAttribute(key, value));
	}
