	@Test
	public void filterWriterWithDisabledCaching() throws Exception {
		final MockHttpServletRequest request = new MockHttpServletRequest("GET", "/hotels");
		MockHttpServletResponse response = new MockHttpServletResponse();

		final byte[] responseBody = "Hello World".getBytes("UTF-8");
		FilterChain filterChain = (filterRequest, filterResponse) -> {
			assertEquals("Invalid request passed", request, filterRequest);
			((HttpServletResponse) filterResponse).setStatus(HttpServletResponse.SC_OK);
			FileCopyUtils.copy(responseBody, filterResponse.getOutputStream());
		};

		ShallowEtagHeaderFilter.disableContentCaching(request);
		this.filter.doFilter(request, response, filterChain);

		assertEquals(200, response.getStatus());
		assertNull(response.getHeader("ETag"));
		assertArrayEquals(responseBody, response.getContentAsByteArray());
	}
