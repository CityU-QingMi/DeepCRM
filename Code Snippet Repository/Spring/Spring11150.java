		@Override
		public Mono<Void> writeTo(ServerWebExchange exchange, Context context) {
			ServerHttpResponse response = exchange.getResponse();
			writeStatusAndHeaders(response);
			return this.inserter.insert(response, new BodyInserter.Context() {
				@Override
				public List<HttpMessageWriter<?>> messageWriters() {
					return context.messageWriters();
				}

				@Override
				public Optional<ServerHttpRequest> serverRequest() {
					return Optional.of(exchange.getRequest());
				}

				@Override
				public Map<String, Object> hints() {
					return hints;
				}
			});
		}
