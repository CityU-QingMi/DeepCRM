	@Test
	public void build() throws Exception {
		ClientRequest result = ClientRequest.method(GET, URI.create("http://example.com"))
				.header("MyKey", "MyValue")
				.cookie("foo", "bar")
				.build();

		MockClientHttpRequest request = new MockClientHttpRequest(GET, "/");
		ExchangeStrategies strategies = mock(ExchangeStrategies.class);

		result.writeTo(request, strategies).block();

		assertEquals("MyValue", request.getHeaders().getFirst("MyKey"));
		assertEquals("bar", request.getCookies().getFirst("foo").getValue());
		StepVerifier.create(request.getBody()).expectComplete().verify();
	}
