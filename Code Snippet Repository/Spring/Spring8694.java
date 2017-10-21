	@Test
	public void requestParameterFromMultiValueMap() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("foo", "bar");
		params.add("foo", "baz");
		this.builder = new MockHttpServletRequestBuilder(HttpMethod.POST, "/foo");
		this.builder.params(params);

		MockHttpServletRequest request = this.builder.buildRequest(this.servletContext);

		assertArrayEquals(new String[] {"bar", "baz"}, request.getParameterMap().get("foo"));
	}
