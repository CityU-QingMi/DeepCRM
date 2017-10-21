	@Test
	public void varArgsTemplateVariables() throws Exception {
		given(requestFactory.createRequest(new URI("http://example.com/hotels/42/bookings/21"), HttpMethod.GET))
				.willReturn(request);
		given(request.execute()).willReturn(response);
		given(errorHandler.hasError(response)).willReturn(false);
		HttpStatus status = HttpStatus.OK;
		given(response.getStatusCode()).willReturn(status);
		given(response.getStatusText()).willReturn(status.getReasonPhrase());

		template.execute("http://example.com/hotels/{hotel}/bookings/{booking}", HttpMethod.GET, null, null, "42",
				"21");

		verify(response).close();
	}
