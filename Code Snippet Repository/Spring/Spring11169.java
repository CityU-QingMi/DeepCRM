	@Override
	public Mono<Object> getHandler(ServerWebExchange exchange) {
		return getHandlerInternal(exchange).map(handler -> {
			if (CorsUtils.isCorsRequest(exchange.getRequest())) {
				CorsConfiguration configA = this.globalCorsConfigSource.getCorsConfiguration(exchange);
				CorsConfiguration configB = getCorsConfiguration(handler, exchange);
				CorsConfiguration config = (configA != null ? configA.combine(configB) : configB);
				if (!getCorsProcessor().process(config, exchange) ||
						CorsUtils.isPreFlightRequest(exchange.getRequest())) {
					return REQUEST_HANDLED_HANDLER;
				}
			}
			return handler;
		});
	}
