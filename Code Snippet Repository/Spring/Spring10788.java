	@Test
	public void payloadReader() throws Exception {
		filter.setIncludePayload(true);

		final MockHttpServletRequest request = new MockHttpServletRequest("POST", "/hotels");
		MockHttpServletResponse response = new MockHttpServletResponse();

		final String requestBody = "Hello World";
		request.setContent(requestBody.getBytes("UTF-8"));

		FilterChain filterChain = new FilterChain() {
			@Override
			public void doFilter(ServletRequest filterRequest, ServletResponse filterResponse)
					throws IOException, ServletException {
				((HttpServletResponse) filterResponse).setStatus(HttpServletResponse.SC_OK);
				String buf = FileCopyUtils.copyToString(filterRequest.getReader());
				assertEquals(requestBody, buf);
			}
		};

		filter.doFilter(request, response, filterChain);

		assertNotNull(filter.afterRequestMessage);
		assertTrue(filter.afterRequestMessage.contains(requestBody));
	}
