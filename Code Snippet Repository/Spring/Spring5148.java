	@Test
	public void decode() {
		DataBuffer fooBuffer = stringBuffer("foo");
		DataBuffer barBuffer = stringBuffer("bar");
		Flux<DataBuffer> source = Flux.just(fooBuffer, barBuffer);
		Flux<ByteBuffer> output = this.decoder.decode(source,
				ResolvableType.forClassWithGenerics(Publisher.class, ByteBuffer.class),
				null, Collections.emptyMap());

		StepVerifier.create(output)
				.expectNext(ByteBuffer.wrap("foo".getBytes()), ByteBuffer.wrap("bar".getBytes()))
				.expectComplete()
				.verify();
	}
