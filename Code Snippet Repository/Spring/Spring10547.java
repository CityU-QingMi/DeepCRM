	@Test
	public void varArgsNullTemplateVariable() throws Exception {
		given(requestFactory.createRequest(new URI("http://example.com/-foo"), HttpMethod.GET))
				.willReturn(request);
		given(request.execute()).willReturn(response);
		given(errorHandler.hasError(response)).willReturn(false);
		HttpStatus status = HttpStatus.OK;
		given(response.getStatusCode()).willReturn(status);
		given(response.getStatusText()).willReturn(status.getReasonPhrase());

		template.execute("http://example.com/{first}-{last}", HttpMethod.GET, null, null, null, "foo");

		verify(response).close();
	}
