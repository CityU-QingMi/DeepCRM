	protected AbstractMonoToListenableFutureAdapter(Mono<S> mono) {
		Assert.notNull(mono, "Mono must not be null");
		this.monoProcessor = mono
				.doOnSuccess(result -> {
					T adapted;
					try {
						adapted = adapt(result);
					}
					catch (Throwable ex) {
						registry.failure(ex);
						return;
					}
					registry.success(adapted);
				})
				.doOnError(this.registry::failure)
				.toProcessor();
	}
