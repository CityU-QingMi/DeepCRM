	@Test
	public void readAsynchronousFileChannel() throws Exception {
		URI uri = DataBufferUtilsTests.class.getResource("DataBufferUtilsTests.txt").toURI();
		AsynchronousFileChannel
				channel = AsynchronousFileChannel.open(Paths.get(uri), StandardOpenOption.READ);
		Flux<DataBuffer> flux = DataBufferUtils.read(channel, this.bufferFactory, 3);

		StepVerifier.create(flux)
				.consumeNextWith(stringConsumer("foo"))
				.consumeNextWith(stringConsumer("bar"))
				.consumeNextWith(stringConsumer("baz"))
				.consumeNextWith(stringConsumer("qux"))
				.expectComplete()
				.verify(Duration.ofSeconds(5));
	}
