	@Test
	public void readString() {
		String body = "data:foo\ndata:bar\n\ndata:baz\n\n";
		MockServerHttpRequest request = MockServerHttpRequest.post("/").body(body);

		Flux<String> data = messageReader.read(ResolvableType.forClass(String.class),
				request, Collections.emptyMap()).cast(String.class);

		StepVerifier.create(data)
				.expectNextMatches(elem -> elem.equals("foo\nbar"))
				.expectNextMatches(elem -> elem.equals("baz"))
				.expectComplete()
				.verify();
	}
