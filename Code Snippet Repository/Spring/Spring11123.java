	@SuppressWarnings("")
	private <T> Mono<T> consumeAndCancel() {
		return (Mono<T>) this.response.getBody()
				.map(buffer -> {
					DataBufferUtils.release(buffer);
					throw new ReadCancellationException();
				})
				.onErrorResume(ReadCancellationException.class, ex -> Mono.empty())
				.then();
	}
