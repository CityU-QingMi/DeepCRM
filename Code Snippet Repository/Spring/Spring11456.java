	@Test
	public void basicAuthenticationAbsentAttributes() throws Exception {
		ClientRequest request = ClientRequest.method(GET, URI.create("http://example.com")).build();
		ClientResponse response = mock(ClientResponse.class);

		ExchangeFunction exchange = r -> {
			assertFalse(r.headers().containsKey(HttpHeaders.AUTHORIZATION));
			return Mono.just(response);
		};

		ExchangeFilterFunction auth = ExchangeFilterFunctions.basicAuthentication();
		assertFalse(request.headers().containsKey(HttpHeaders.AUTHORIZATION));
		ClientResponse result = auth.filter(request, exchange).block();
		assertEquals(response, result);
	}
