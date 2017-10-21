	@Test
	public void apply() throws Exception {
		ClientRequest request = ClientRequest.method(GET, URI.create("http://example.com")).build();
		ClientResponse response = mock(ClientResponse.class);
		ExchangeFunction exchange = r -> Mono.just(response);

		boolean[] filterInvoked = new boolean[1];
		ExchangeFilterFunction filter = (r, n) -> {
			assertFalse(filterInvoked[0]);
			filterInvoked[0] = true;
			return n.exchange(r);
		};

		ExchangeFunction filteredExchange = filter.apply(exchange);
		ClientResponse result = filteredExchange.exchange(request).block();
		assertEquals(response, result);
		assertTrue(filterInvoked[0]);
	}
