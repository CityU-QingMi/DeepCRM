	@Test
	public void mapTemplateVariables() throws Exception {
		given(requestFactory.createRequest(new URI("http://example.com/hotels/42/bookings/42"), HttpMethod.GET))
				.willReturn(request);
		given(request.execute()).willReturn(response);
		given(errorHandler.hasError(response)).willReturn(false);
		HttpStatus status = HttpStatus.OK;
		given(response.getStatusCode()).willReturn(status);
		given(response.getStatusText()).willReturn(status.getReasonPhrase());

		Map<String, String> vars = Collections.singletonMap("hotel", "42");
		template.execute("http://example.com/hotels/{hotel}/bookings/{hotel}", HttpMethod.GET, null, null, vars);

		verify(response).close();
	}
