	@Test
	@SuppressWarnings("")
	public void buildRequestBasicAuth() {
		String base64Credentials = "dXNlcm5hbWU6cGFzc3dvcmQ=";
		String authzHeaderValue = "Basic: " + base64Credentials;
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(base64Credentials);
		webRequest.setCredentials(credentials);
		webRequest.setAdditionalHeader("Authorization", authzHeaderValue);

		MockHttpServletRequest actualRequest = requestBuilder.buildRequest(servletContext);

		assertThat(actualRequest.getAuthType(), equalTo("Basic"));
		assertThat(actualRequest.getHeader("Authorization"), equalTo(authzHeaderValue));
	}
