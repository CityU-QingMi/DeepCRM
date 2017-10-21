	@Test
	@SuppressWarnings("")
	public void buildRequestInputStream() throws Exception {
		String content = "some content that has length";
		webRequest.setHttpMethod(HttpMethod.POST);
		webRequest.setRequestBody(content);

		MockHttpServletRequest actualRequest = requestBuilder.buildRequest(servletContext);

		assertThat(IOUtils.toString(actualRequest.getInputStream()), equalTo(content));
	}
