	@Test
	public void payloadInputStream() throws Exception {
		filter.setIncludePayload(true);

		final MockHttpServletRequest request = new MockHttpServletRequest("POST", "/hotels");
		MockHttpServletResponse response = new MockHttpServletResponse();

		final byte[] requestBody = "Hello World".getBytes("UTF-8");
		request.setContent(requestBody);

		FilterChain filterChain = new FilterChain() {
			@Override
			public void doFilter(ServletRequest filterRequest, ServletResponse filterResponse)
					throws IOException, ServletException {
				((HttpServletResponse) filterResponse).setStatus(HttpServletResponse.SC_OK);
				byte[] buf = FileCopyUtils.copyToByteArray(filterRequest.getInputStream());
				assertArrayEquals(requestBody, buf);
			}
		};

		filter.doFilter(request, response, filterChain);

		assertNotNull(filter.afterRequestMessage);
		assertTrue(filter.afterRequestMessage.contains("Hello World"));
	}
