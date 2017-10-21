	@Test
	public void requestParameterFromQueryNull() {
		this.builder = new MockHttpServletRequestBuilder(HttpMethod.GET, "/?foo");

		MockHttpServletRequest request = this.builder.buildRequest(this.servletContext);
		Map<String, String[]> parameterMap = request.getParameterMap();

		assertArrayEquals(new String[] {null}, parameterMap.get("foo"));
		assertEquals("foo", request.getQueryString());
	}
