	@Test
	public void buildResponseHeadersNullDomainDefaulted() throws Exception {
		Cookie cookie = new Cookie("cookieA", "valueA");
		this.response.addCookie(cookie);
		WebResponse webResponse = this.responseBuilder.build();

		List<NameValuePair> responseHeaders = webResponse.getResponseHeaders();
		assertThat(responseHeaders.size(), equalTo(1));
		NameValuePair header = responseHeaders.get(0);
		assertThat(header.getName(), equalTo("Set-Cookie"));
		assertThat(header.getValue(), equalTo("cookieA=valueA"));
	}
