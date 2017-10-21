	@Test
	public void put() throws Exception {
		given(converter.canWrite(String.class, null)).willReturn(true);
		given(requestFactory.createRequest(new URI("http://example.com"), HttpMethod.PUT)).willReturn(request);
		String helloWorld = "Hello World";
		converter.write(helloWorld, null, request);
		given(request.execute()).willReturn(response);
		given(errorHandler.hasError(response)).willReturn(false);
		HttpStatus status = HttpStatus.OK;
		given(response.getStatusCode()).willReturn(status);
		given(response.getStatusText()).willReturn(status.getReasonPhrase());

		template.put("http://example.com", helloWorld);

		verify(response).close();
	}
