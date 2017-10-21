	@Test
	public void alwaysInclude() throws Exception {
		given(request.getAttribute(View.PATH_VARIABLES)).willReturn(null);
		given(request.getRequestDispatcher(url)).willReturn(new MockRequestDispatcher(url));

		view.setUrl(url);
		view.setAlwaysInclude(true);

		// Can now try multiple tests
		view.render(model, request, response);
		assertEquals(url, response.getIncludedUrl());

		model.forEach((key, value) -> verify(request).setAttribute(key, value));
	}
