	public static Flux<DataBuffer> write(Publisher<DataBuffer> source, AsynchronousFileChannel channel,
			long position) {

		Assert.notNull(source, "'source' must not be null");
		Assert.notNull(channel, "'channel' must not be null");
		Assert.isTrue(position >= 0, "'position' must be >= 0");

		Flux<DataBuffer> flux = Flux.from(source);

		return Flux.create(sink -> {
			BaseSubscriber<DataBuffer> subscriber =
					new AsynchronousFileChannelWriteCompletionHandler(sink, channel, position);
			flux.subscribe(subscriber);
		});
	}
