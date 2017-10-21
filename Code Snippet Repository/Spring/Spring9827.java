	@Override
	public Mono<Void> filter(ServerWebExchange exchange) {
		return Mono.defer(() -> {
			if (this.index < this.filters.size()) {
				WebFilter filter = this.filters.get(this.index);
				WebFilterChain chain = new DefaultWebFilterChain(this, this.index + 1);
				return filter.filter(exchange, chain);
			}
			else {
				return this.handler.handle(exchange);
			}
		});
	}
