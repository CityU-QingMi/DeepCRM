	@Test
	public void buildRequestContentType() {
		String contentType = "text/html;charset=UTF-8";
		webRequest.setAdditionalHeader("Content-Type", contentType);

		MockHttpServletRequest actualRequest = requestBuilder.buildRequest(servletContext);

		assertThat(actualRequest.getContentType(), equalTo(contentType));
		assertThat(actualRequest.getHeader("Content-Type"), equalTo(contentType));
	}
