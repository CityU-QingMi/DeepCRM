	@Test
	public void writeWritableByteChannel() throws Exception {
		DataBuffer foo = stringBuffer("foo");
		DataBuffer bar = stringBuffer("bar");
		DataBuffer baz = stringBuffer("baz");
		DataBuffer qux = stringBuffer("qux");
		Flux<DataBuffer> flux = Flux.just(foo, bar, baz, qux);

		Path tempFile = Files.createTempFile("DataBufferUtilsTests", null);
		WritableByteChannel channel = Files.newByteChannel(tempFile, StandardOpenOption.WRITE);

		Flux<DataBuffer> writeResult = DataBufferUtils.write(flux, channel);
		StepVerifier.create(writeResult)
				.consumeNextWith(stringConsumer("foo"))
				.consumeNextWith(stringConsumer("bar"))
				.consumeNextWith(stringConsumer("baz"))
				.consumeNextWith(stringConsumer("qux"))
				.expectComplete()
				.verify(Duration.ofSeconds(5));

		String result = Files.readAllLines(tempFile)
				.stream()
				.collect(Collectors.joining());

		assertEquals("foobarbazqux", result);
		channel.close();
	}
