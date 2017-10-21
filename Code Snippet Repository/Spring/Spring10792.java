	@Test
	public void filterFlushResponse() throws Exception {
		final MockHttpServletRequest request = new MockHttpServletRequest("GET", "/hotels");
		MockHttpServletResponse response = new MockHttpServletResponse();

		final byte[] responseBody = "Hello World".getBytes("UTF-8");
		FilterChain filterChain = (filterRequest, filterResponse) -> {
			assertEquals("Invalid request passed", request, filterRequest);
			((HttpServletResponse) filterResponse).setStatus(HttpServletResponse.SC_OK);
			FileCopyUtils.copy(responseBody, filterResponse.getOutputStream());
			filterResponse.flushBuffer();
		};
		filter.doFilter(request, response, filterChain);

		assertEquals("Invalid status", 200, response.getStatus());
		assertEquals("Invalid ETag header", "\"0b10a8db164e0754105b7a99be72e3fe5\"", response.getHeader("ETag"));
		assertTrue("Invalid Content-Length header", response.getContentLength() > 0);
		assertArrayEquals("Invalid content", responseBody, response.getContentAsByteArray());
	}
