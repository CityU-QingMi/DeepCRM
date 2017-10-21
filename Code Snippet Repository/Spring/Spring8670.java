	@Test
	public void buildRequestReader() throws Exception {
		String expectedBody = "request body";
		webRequest.setHttpMethod(HttpMethod.POST);
		webRequest.setRequestBody(expectedBody);

		MockHttpServletRequest actualRequest = requestBuilder.buildRequest(servletContext);

		assertThat(IOUtils.toString(actualRequest.getReader()), equalTo(expectedBody));
	}
