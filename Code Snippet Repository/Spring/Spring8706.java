	@Test
	public void mergeInvokesDefaultRequestPostProcessorFirst() {
		final String ATTR = "ATTR";
		final String EXPECTED = "override";

		MockHttpServletRequestBuilder defaultBuilder =
				new MockHttpServletRequestBuilder(HttpMethod.GET, "/foo/bar")
						.with(requestAttr(ATTR).value("default"))
						.with(requestAttr(ATTR).value(EXPECTED));

		builder.merge(defaultBuilder);

		MockHttpServletRequest request = builder.buildRequest(servletContext);
		request = builder.postProcessRequest(request);

		assertEquals(EXPECTED, request.getAttribute(ATTR));
	}
