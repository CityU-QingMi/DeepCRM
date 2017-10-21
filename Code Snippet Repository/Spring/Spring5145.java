	@Test
	public void decode() {
		DataBuffer fooBuffer = stringBuffer("foo");
		DataBuffer barBuffer = stringBuffer("bar");
		Flux<DataBuffer> source = Flux.just(fooBuffer, barBuffer);
		Flux<byte[]> output = this.decoder.decode(source,
				ResolvableType.forClassWithGenerics(Publisher.class, byte[].class),
				null, Collections.emptyMap());

		StepVerifier.create(output)
				.consumeNextWith(bytes -> assertArrayEquals("foo".getBytes(), bytes))
				.consumeNextWith(bytes -> assertArrayEquals("bar".getBytes(), bytes))
				.expectComplete()
				.verify();
	}
