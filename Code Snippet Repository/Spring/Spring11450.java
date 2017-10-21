	@Test
	public void attributes() {
		ExchangeFilterFunction filter = (request, next) -> {
			assertEquals("bar", request.attributes().get("foo"));
			return next.exchange(request);
		};

		WebClient client = builder().filter(filter).build();

		client.get().uri("/path").attribute("foo", "bar").exchange();
	}
