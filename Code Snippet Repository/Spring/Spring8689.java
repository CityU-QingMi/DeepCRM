	@Test
	public void buildResponseHeaders() throws Exception {
		this.response.addHeader("Content-Type", "text/html");
		this.response.addHeader("X-Test", "value");
		Cookie cookie = new Cookie("cookieA", "valueA");
		cookie.setDomain("domain");
		cookie.setPath("/path");
		cookie.setMaxAge(1800);
		cookie.setSecure(true);
		cookie.setHttpOnly(true);
		this.response.addCookie(cookie);
		WebResponse webResponse = this.responseBuilder.build();

		List<NameValuePair> responseHeaders = webResponse.getResponseHeaders();
		assertThat(responseHeaders.size(), equalTo(3));
		NameValuePair header = responseHeaders.get(0);
		assertThat(header.getName(), equalTo("Content-Type"));
		assertThat(header.getValue(), equalTo("text/html"));
		header = responseHeaders.get(1);
		assertThat(header.getName(), equalTo("X-Test"));
		assertThat(header.getValue(), equalTo("value"));
		header = responseHeaders.get(2);
		assertThat(header.getName(), equalTo("Set-Cookie"));
		assertThat(header.getValue(), startsWith("cookieA=valueA; Path=/path; Domain=domain; Max-Age=1800; Expires="));
		assertThat(header.getValue(), endsWith("; Secure; HttpOnly"));
	}
