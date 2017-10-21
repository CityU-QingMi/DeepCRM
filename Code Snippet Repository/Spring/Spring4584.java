	public static Flux<DataBuffer> write(Publisher<DataBuffer> source,
			WritableByteChannel channel) {

		Assert.notNull(source, "'source' must not be null");
		Assert.notNull(channel, "'channel' must not be null");

		Flux<DataBuffer> flux = Flux.from(source);

		return Flux.create(sink ->
				flux.subscribe(dataBuffer -> {
							try {
								ByteBuffer byteBuffer = dataBuffer.asByteBuffer();
								while (byteBuffer.hasRemaining()) {
									channel.write(byteBuffer);
								}
								sink.next(dataBuffer);
							}
							catch (IOException ex) {
								sink.error(ex);
							}

						},
						sink::error,
						sink::complete));
	}
