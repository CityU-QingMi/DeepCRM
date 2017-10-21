	@Test
	public void encode() throws Exception {
		String s = "foo";
		Resource resource = new ByteArrayResource(s.getBytes(StandardCharsets.UTF_8));

		Mono<Resource> source = Mono.just(resource);

		Flux<DataBuffer> output = this.encoder.encode(source, this.bufferFactory,
				ResolvableType.forClass(Resource.class),
				null, Collections.emptyMap());

		StepVerifier.create(output)
				.consumeNextWith(stringConsumer(s))
				.expectComplete()
				.verify();
	}
