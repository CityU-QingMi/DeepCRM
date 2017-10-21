	@Test
	public void readUnalignedChannel() throws Exception {
		URI uri = DataBufferUtilsTests.class.getResource("DataBufferUtilsTests.txt").toURI();
		FileChannel channel = FileChannel.open(Paths.get(uri), StandardOpenOption.READ);
		Flux<DataBuffer> flux = DataBufferUtils.read(channel, this.bufferFactory, 5);

		StepVerifier.create(flux)
				.consumeNextWith(stringConsumer("fooba"))
				.consumeNextWith(stringConsumer("rbazq"))
				.consumeNextWith(stringConsumer("ux"))
				.expectComplete()
				.verify(Duration.ofSeconds(5));

		assertFalse(channel.isOpen());
	}
