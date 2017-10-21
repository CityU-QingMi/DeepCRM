	@Test
	public void preExistingHeadersFromHttpServletResponse() {
		String headerName = "Access-Control-Allow-Origin";
		String headerValue = "localhost:8080";

		this.mockResponse.addHeader(headerName, headerValue);
		this.response = new ServletServerHttpResponse(this.mockResponse);

		assertEquals(headerValue, this.response.getHeaders().getFirst(headerName));
		assertEquals(Collections.singletonList(headerValue), this.response.getHeaders().get(headerName));
		assertTrue(this.response.getHeaders().containsKey(headerName));
		assertEquals(headerValue, this.response.getHeaders().getFirst(headerName));
		assertEquals(headerValue, this.response.getHeaders().getAccessControlAllowOrigin());
	}
