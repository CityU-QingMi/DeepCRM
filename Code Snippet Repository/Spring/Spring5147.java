	@Test
	public void encode() {
		byte[] fooBytes = "foo".getBytes(StandardCharsets.UTF_8);
		byte[] barBytes = "bar".getBytes(StandardCharsets.UTF_8);
		Flux<byte[]> source = Flux.just(fooBytes, barBytes);

		Flux<DataBuffer> output = this.encoder.encode(source, this.bufferFactory,
				ResolvableType.forClassWithGenerics(Publisher.class, ByteBuffer.class),
				null, Collections.emptyMap());

		StepVerifier.create(output)
				.consumeNextWith(b -> {
					byte[] buf = new byte[3];
					b.read(buf);
					assertArrayEquals(fooBytes, buf);
				})
				.consumeNextWith(b -> {
					byte[] buf = new byte[3];
					b.read(buf);
					assertArrayEquals(barBytes, buf);
				})
				.expectComplete()
				.verify();
	}
