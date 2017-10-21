	@Test
	public void readInputStream() throws Exception {
		InputStream is = DataBufferUtilsTests.class.getResourceAsStream("DataBufferUtilsTests.txt");
		Flux<DataBuffer> flux = DataBufferUtils.read(is, this.bufferFactory, 3);

		StepVerifier.create(flux)
				.consumeNextWith(stringConsumer("foo"))
				.consumeNextWith(stringConsumer("bar"))
				.consumeNextWith(stringConsumer("baz"))
				.consumeNextWith(stringConsumer("qux"))
				.expectComplete()
				.verify(Duration.ofSeconds(5));
	}
