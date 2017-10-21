	@Test
	public void postForLocationNoLocation() throws Exception {
		given(requestFactory.createRequest(new URI("http://example.com"), HttpMethod.POST)).willReturn(request);
		String helloWorld = "Hello World";
		given(converter.canWrite(String.class, null)).willReturn(true);
		converter.write(helloWorld, null, request);
		given(request.execute()).willReturn(response);
		given(errorHandler.hasError(response)).willReturn(false);
		HttpHeaders responseHeaders = new HttpHeaders();
		given(response.getHeaders()).willReturn(responseHeaders);
		HttpStatus status = HttpStatus.OK;
		given(response.getStatusCode()).willReturn(status);
		given(response.getStatusText()).willReturn(status.getReasonPhrase());

		URI result = template.postForLocation("http://example.com", helloWorld);
		assertNull("Invalid POST result", result);

		verify(response).close();
	}
