	@Override
	public final Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {

		// After Reactor Netty #171 is fixed we can return without delegating

		return getDelegate().writeWith(
				Flux.from(body)
						.reduce(0, (current, buffer) -> {
							int next = current + buffer.readableByteCount();
							DataBufferUtils.release(buffer);
							return next;
						})
						.doOnNext(count -> getHeaders().setContentLength(count))
						.then(Mono.empty()));
	}
