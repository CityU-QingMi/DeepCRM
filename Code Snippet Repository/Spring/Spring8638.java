	private HeaderAssertions headerAssertions(HttpHeaders responseHeaders) {

		MockClientHttpRequest request = new MockClientHttpRequest(HttpMethod.GET, URI.create("/"));
		MockClientHttpResponse response = new MockClientHttpResponse(HttpStatus.OK);
		response.getHeaders().putAll(responseHeaders);

		WiretapClientHttpRequest wiretapRequest = new WiretapClientHttpRequest(request);
		WiretapClientHttpResponse wiretapResponse = new WiretapClientHttpResponse(response);

		ExchangeResult result = new ExchangeResult(wiretapRequest, wiretapResponse);
		return new HeaderAssertions(result, mock(WebTestClient.ResponseSpec.class));
	}
