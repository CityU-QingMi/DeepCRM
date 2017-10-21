	public static Flux<DataBuffer> read(AsynchronousFileChannel channel,
			long position, DataBufferFactory dataBufferFactory, int bufferSize) {

		Assert.notNull(channel, "'channel' must not be null");
		Assert.notNull(dataBufferFactory, "'dataBufferFactory' must not be null");
		Assert.isTrue(position >= 0, "'position' must be >= 0");

		ByteBuffer byteBuffer = ByteBuffer.allocate(bufferSize);

		return Flux.create(sink -> {
			sink.onDispose(() -> closeChannel(channel));
			AsynchronousFileChannelReadCompletionHandler completionHandler =
					new AsynchronousFileChannelReadCompletionHandler(sink, position,
							dataBufferFactory, byteBuffer);
			channel.read(byteBuffer, position, channel, completionHandler);
		});
	}
