	@Override
	public boolean process(@Nullable CorsConfiguration config, ServerWebExchange exchange) {

		ServerHttpRequest request = exchange.getRequest();
		ServerHttpResponse response = exchange.getResponse();

		if (!CorsUtils.isCorsRequest(request)) {
			return true;
		}

		if (responseHasCors(response)) {
			logger.debug("Skip CORS: response already contains \"Access-Control-Allow-Origin\" header");
			return true;
		}

		if (CorsUtils.isSameOrigin(request)) {
			logger.debug("Skip CORS: request is from same origin");
			return true;
		}

		boolean preFlightRequest = CorsUtils.isPreFlightRequest(request);
		if (config == null) {
			if (preFlightRequest) {
				rejectRequest(response);
				return false;
			}
			else {
				return true;
			}
		}

		return handleInternal(exchange, config, preFlightRequest);
	}
