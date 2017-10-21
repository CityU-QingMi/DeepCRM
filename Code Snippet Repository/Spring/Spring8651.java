	private StatusAssertions statusAssertions(HttpStatus status) {

		MockClientHttpRequest request = new MockClientHttpRequest(HttpMethod.GET, URI.create("/"));
		MockClientHttpResponse response = new MockClientHttpResponse(status);

		WiretapClientHttpRequest wiretapRequest = new WiretapClientHttpRequest(request);
		WiretapClientHttpResponse wiretapResponse = new WiretapClientHttpResponse(response);

		ExchangeResult exchangeResult = new ExchangeResult(wiretapRequest, wiretapResponse);
		return new StatusAssertions(exchangeResult, mock(WebTestClient.ResponseSpec.class));
	}
