	@Test
	public void captureAndClaim() throws Exception {
		ClientHttpRequest request = new MockClientHttpRequest(HttpMethod.GET, "/test");
		ClientHttpResponse response = new MockClientHttpResponse(HttpStatus.OK);
		ClientHttpConnector connector = (method, uri, fn) -> fn.apply(request).then(Mono.just(response));

		ClientRequest clientRequest = ClientRequest.method(HttpMethod.GET, URI.create("/test"))
				.header(WebTestClient.WEBTESTCLIENT_REQUEST_ID, "1").build();

		WiretapConnector wiretapConnector = new WiretapConnector(connector);
		ExchangeFunction function = ExchangeFunctions.create(wiretapConnector);
		function.exchange(clientRequest).block(ofMillis(0));

		ExchangeResult actual = wiretapConnector.claimRequest("1");
		assertNotNull(actual);
		assertEquals(HttpMethod.GET, actual.getMethod());
		assertEquals("/test", actual.getUrl().toString());
	}
