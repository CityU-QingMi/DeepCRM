	@Test
	public void readPojo() {
		MockServerHttpRequest request = MockServerHttpRequest.post("/").body(
				"data:{\"foo\": \"foofoo\", \"bar\": \"barbar\"}\n\n" +
				"data:{\"foo\": \"foofoofoo\", \"bar\": \"barbarbar\"}\n\n");

		Flux<Pojo> data = messageReader.read(ResolvableType.forClass(Pojo.class), request,
				Collections.emptyMap()).cast(Pojo.class);

		StepVerifier.create(data)
				.consumeNextWith(pojo -> {
					assertEquals("foofoo", pojo.getFoo());
					assertEquals("barbar", pojo.getBar());
				})
				.consumeNextWith(pojo -> {
					assertEquals("foofoofoo", pojo.getFoo());
					assertEquals("barbarbar", pojo.getBar());
				})
				.expectComplete()
				.verify();
	}
