		@Override
		public Mono<HandlerFunction<T>> route(ServerRequest serverRequest) {
			return this.predicate.nest(serverRequest)
					.map(nestedRequest -> {
								if (logger.isDebugEnabled()) {
									logger.debug(
											String.format(
													"Nested predicate \"%s\" matches against \"%s\"",
													this.predicate, serverRequest));
								}
								return this.routerFunction.route(nestedRequest);
							}
					)
					.orElseGet(Mono::empty);
		}
