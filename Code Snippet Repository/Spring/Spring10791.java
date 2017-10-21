	@Test
	public void filterSendRedirect() throws Exception {
		final MockHttpServletRequest request = new MockHttpServletRequest("GET", "/hotels");
		MockHttpServletResponse response = new MockHttpServletResponse();

		final byte[] responseBody = "Hello World".getBytes("UTF-8");
		FilterChain filterChain = (filterRequest, filterResponse) -> {
			assertEquals("Invalid request passed", request, filterRequest);
			response.setContentLength(100);
			FileCopyUtils.copy(responseBody, filterResponse.getOutputStream());
			((HttpServletResponse) filterResponse).sendRedirect("http://www.google.com");
		};
		filter.doFilter(request, response, filterChain);

		assertEquals("Invalid status", 302, response.getStatus());
		assertNull("Invalid ETag header", response.getHeader("ETag"));
		assertEquals("Invalid Content-Length header", 100, response.getContentLength());
		assertArrayEquals("Invalid content", responseBody, response.getContentAsByteArray());
		assertEquals("Invalid redirect URL", "http://www.google.com", response.getRedirectedUrl());
	}
