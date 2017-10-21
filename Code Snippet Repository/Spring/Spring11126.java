		@Override
		public ResponseSpec onStatus(Predicate<HttpStatus> statusPredicate,
				Function<ClientResponse, Mono<? extends Throwable>> exceptionFunction) {

			Assert.notNull(statusPredicate, "'statusPredicate' must not be null");
			Assert.notNull(exceptionFunction, "'exceptionFunction' must not be null");

			if (this.statusHandlers.size() == 1 && this.statusHandlers.get(0) == DEFAULT_STATUS_HANDLER) {
				this.statusHandlers.clear();
			}

			this.statusHandlers.add(new StatusHandler(statusPredicate, exceptionFunction));

			return this;
		}
