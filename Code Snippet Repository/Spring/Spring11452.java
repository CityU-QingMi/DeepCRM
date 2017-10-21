	@Test
	public void andThen() throws Exception {
		ClientRequest request = ClientRequest.method(GET, URI.create("http://example.com")).build();
		ClientResponse response = mock(ClientResponse.class);
		ExchangeFunction exchange = r -> Mono.just(response);

		boolean[] filtersInvoked = new boolean[2];
		ExchangeFilterFunction filter1 = (r, n) -> {
			assertFalse(filtersInvoked[0]);
			assertFalse(filtersInvoked[1]);
			filtersInvoked[0] = true;
			assertFalse(filtersInvoked[1]);
			return n.exchange(r);
		};
		ExchangeFilterFunction filter2 = (r, n) -> {
			assertTrue(filtersInvoked[0]);
			assertFalse(filtersInvoked[1]);
			filtersInvoked[1] = true;
			return n.exchange(r);
		};
		ExchangeFilterFunction filter = filter1.andThen(filter2);


		ClientResponse result = filter.filter(request, exchange).block();
		assertEquals(response, result);

		assertTrue(filtersInvoked[0]);
		assertTrue(filtersInvoked[1]);
	}
