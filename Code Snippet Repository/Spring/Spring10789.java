	@Test
	public void payloadMaxLength() throws Exception {
		filter.setIncludePayload(true);
		filter.setMaxPayloadLength(3);

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
				ContentCachingRequestWrapper wrapper =
						WebUtils.getNativeRequest(filterRequest, ContentCachingRequestWrapper.class);
				assertArrayEquals("Hel".getBytes("UTF-8"), wrapper.getContentAsByteArray());
			}
		};

		filter.doFilter(request, response, filterChain);

		assertNotNull(filter.afterRequestMessage);
		assertTrue(filter.afterRequestMessage.contains("Hel"));
		assertFalse(filter.afterRequestMessage.contains("Hello World"));
	}
