	@Test
	public void requestParameterFromQueryList() {
		this.builder = new MockHttpServletRequestBuilder(HttpMethod.GET, "/?foo[0]=bar&foo[1]=baz");

		MockHttpServletRequest request = this.builder.buildRequest(this.servletContext);

		assertEquals("foo%5B0%5D=bar&foo%5B1%5D=baz", request.getQueryString());
		assertEquals("bar", request.getParameter("foo[0]"));
		assertEquals("baz", request.getParameter("foo[1]"));
	}
