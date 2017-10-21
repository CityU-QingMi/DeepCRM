	public static Flux<DataBuffer> read(ReadableByteChannel channel,
			DataBufferFactory dataBufferFactory, int bufferSize) {

		Assert.notNull(channel, "ReadableByteChannel must not be null");
		Assert.notNull(dataBufferFactory, "DataBufferFactory must not be null");

		return Flux.generate(() -> channel,
				new ReadableByteChannelGenerator(dataBufferFactory, bufferSize),
				DataBufferUtils::closeChannel);
	}
