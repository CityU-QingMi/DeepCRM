	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		if (CorsUtils.isCorsRequest(request)) {
			CorsConfiguration corsConfiguration = this.configSource.getCorsConfiguration(exchange);
			if (corsConfiguration != null) {
				boolean isValid = this.processor.process(corsConfiguration, exchange);
				if (!isValid || CorsUtils.isPreFlightRequest(request)) {
					return Mono.empty();
				}
			}
		}
		return chain.filter(exchange);
	}
