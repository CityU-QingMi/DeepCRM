	@Test
	public void buildRequestParameterMapFromMultipleQueryParams() throws Exception {
		webRequest.setUrl(new URL("http://example.com/example/?name=value&param2=value+2"));

		MockHttpServletRequest actualRequest = requestBuilder.buildRequest(servletContext);

		assertThat(actualRequest.getParameterMap().size(), equalTo(2));
		assertThat(actualRequest.getParameter("name"), equalTo("value"));
		assertThat(actualRequest.getParameter("param2"), equalTo("value 2"));
	}
