		@Override
		public Mono<HandlerFunction<T>> route(ServerRequest request) {
			if (this.predicate.test(request)) {
				if (logger.isDebugEnabled()) {
					logger.debug(String.format("Predicate \"%s\" matches against \"%s\"",
							this.predicate, request));
				}
				return Mono.just(this.handlerFunction);
			}
			else {
				return Mono.empty();
			}
		}
