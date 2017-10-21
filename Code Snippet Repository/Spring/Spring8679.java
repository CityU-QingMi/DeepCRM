	@Test
	public void buildRequestContentLength() {
		String content = "some content that has length";
		webRequest.setHttpMethod(HttpMethod.POST);
		webRequest.setRequestBody(content);

		MockHttpServletRequest actualRequest = requestBuilder.buildRequest(servletContext);

		assertThat(actualRequest.getContentLength(), equalTo(content.length()));
	}
