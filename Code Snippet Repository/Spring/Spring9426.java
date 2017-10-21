	@Override
	protected final Mono<Void> writeAndFlushWithInternal(
			Publisher<? extends Publisher<? extends DataBuffer>> body) {

		if (this.writeCalled.compareAndSet(false, true)) {
			Processor<? super Publisher<? extends DataBuffer>, Void> processor = createBodyFlushProcessor();
			return Mono.from(subscriber -> {
				body.subscribe(processor);
				processor.subscribe(subscriber);
			});
		}
		return Mono.error(new IllegalStateException(
				"writeWith() or writeAndFlushWith() has already been called"));
	}
