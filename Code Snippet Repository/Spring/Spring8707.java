	@Test
	public void arbitraryMethod() {
		String httpMethod = "REPort";
		URI url = UriComponentsBuilder.fromPath("/foo/{bar}").buildAndExpand(42).toUri();
		this.builder = new MockHttpServletRequestBuilder(httpMethod, url);
		MockHttpServletRequest request = this.builder.buildRequest(this.servletContext);

		assertEquals(httpMethod, request.getMethod());
		assertEquals("/foo/42", request.getPathInfo());
	}
