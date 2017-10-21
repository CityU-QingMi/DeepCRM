	@Test
	public void filterSendErrorMessage() throws Exception {
		final MockHttpServletRequest request = new MockHttpServletRequest("GET", "/hotels");
		MockHttpServletResponse response = new MockHttpServletResponse();

		final byte[] responseBody = "Hello World".getBytes("UTF-8");
		FilterChain filterChain = (filterRequest, filterResponse) -> {
			assertEquals("Invalid request passed", request, filterRequest);
			response.setContentLength(100);
			FileCopyUtils.copy(responseBody, filterResponse.getOutputStream());
			((HttpServletResponse) filterResponse).sendError(HttpServletResponse.SC_FORBIDDEN, "ERROR");
		};
		filter.doFilter(request, response, filterChain);

		assertEquals("Invalid status", 403, response.getStatus());
		assertNull("Invalid ETag header", response.getHeader("ETag"));
		assertEquals("Invalid Content-Length header", 100, response.getContentLength());
		assertArrayEquals("Invalid content", responseBody, response.getContentAsByteArray());
		assertEquals("Invalid error message", "ERROR", response.getErrorMessage());
	}
