	@Test
	public void uriTemplateWithTrailingSlash() throws Exception {
		String url = "http://example.com/spring/";
		given(requestFactory.createRequest(new URI(url), HttpMethod.GET)).willReturn(request);
		given(request.execute()).willReturn(response);
		given(errorHandler.hasError(response)).willReturn(false);
		HttpStatus status = HttpStatus.OK;
		given(response.getStatusCode()).willReturn(status);
		given(response.getStatusText()).willReturn(status.getReasonPhrase());

		template.execute(url, HttpMethod.GET, null, null);

		verify(response).close();
	}
