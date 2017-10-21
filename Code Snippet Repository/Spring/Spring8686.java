	@Test
	public void buildRequestParameterMapViaWebRequestDotSetRequestParametersWithMultipleRequestParams() {
		webRequest.setRequestParameters(asList(new NameValuePair("name1", "value1"), new NameValuePair("name2", "value2")));

		MockHttpServletRequest actualRequest = requestBuilder.buildRequest(servletContext);

		assertThat(actualRequest.getParameterMap().size(), equalTo(2));
		assertThat(actualRequest.getParameter("name1"), equalTo("value1"));
		assertThat(actualRequest.getParameter("name2"), equalTo("value2"));
	}
