	@Test
	public void delete() throws Exception {
		given(requestFactory.createRequest(new URI("http://example.com"), HttpMethod.DELETE)).willReturn(request);
		given(request.execute()).willReturn(response);
		given(errorHandler.hasError(response)).willReturn(false);
		HttpStatus status = HttpStatus.OK;
		given(response.getStatusCode()).willReturn(status);
		given(response.getStatusText()).willReturn(status.getReasonPhrase());

		template.delete("http://example.com");

		verify(response).close();
	}
