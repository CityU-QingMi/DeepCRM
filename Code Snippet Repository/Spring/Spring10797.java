	@Test
	public void filterWriter() throws Exception {
		final MockHttpServletRequest request = new MockHttpServletRequest("GET", "/hotels");
		String etag = "\"0b10a8db164e0754105b7a99be72e3fe5\"";
		request.addHeader("If-None-Match", etag);
		MockHttpServletResponse response = new MockHttpServletResponse();

		FilterChain filterChain = (filterRequest, filterResponse) -> {
			assertEquals("Invalid request passed", request, filterRequest);
			((HttpServletResponse) filterResponse).setStatus(HttpServletResponse.SC_OK);
			String responseBody = "Hello World";
			FileCopyUtils.copy(responseBody, filterResponse.getWriter());
		};
		filter.doFilter(request, response, filterChain);

		assertEquals("Invalid status", 304, response.getStatus());
		assertEquals("Invalid ETag header", "\"0b10a8db164e0754105b7a99be72e3fe5\"", response.getHeader("ETag"));
		assertFalse("Response has Content-Length header", response.containsHeader("Content-Length"));
		assertArrayEquals("Invalid content", new byte[0], response.getContentAsByteArray());
	}
