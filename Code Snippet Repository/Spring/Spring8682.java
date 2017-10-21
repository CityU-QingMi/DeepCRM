	@Test
	public void buildRequestContentTypeWithFormSubmission() {
		webRequest.setEncodingType(FormEncodingType.URL_ENCODED);

		MockHttpServletRequest actualRequest = requestBuilder.buildRequest(servletContext);

		assertThat(actualRequest.getContentType(), equalTo("application/x-www-form-urlencoded"));
		assertThat(actualRequest.getHeader("Content-Type"),
				equalTo("application/x-www-form-urlencoded;charset=ISO-8859-1"));
	}
