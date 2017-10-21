	@Test
	public void requestParameterFromRequestBodyFormData() throws Exception {
		String contentType = "application/x-www-form-urlencoded;charset=UTF-8";
		String body = "name+1=value+1&name+2=value+A&name+2=value+B&name+3";

		MockHttpServletRequest request = new MockHttpServletRequestBuilder(HttpMethod.POST, "/foo")
				.contentType(contentType).content(body.getBytes(StandardCharsets.UTF_8))
				.buildRequest(this.servletContext);

		assertArrayEquals(new String[] {"value 1"}, request.getParameterMap().get("name 1"));
		assertArrayEquals(new String[] {"value A", "value B"}, request.getParameterMap().get("name 2"));
		assertArrayEquals(new String[] {null}, request.getParameterMap().get("name 3"));
	}
