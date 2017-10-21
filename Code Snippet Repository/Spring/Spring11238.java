	@Override
	@Nullable
	public RequestMethodsRequestCondition getMatchingCondition(ServerWebExchange exchange) {
		if (CorsUtils.isPreFlightRequest(exchange.getRequest())) {
			return matchPreFlight(exchange.getRequest());
		}
		if (getMethods().isEmpty()) {
			if (RequestMethod.OPTIONS.name().equals(exchange.getRequest().getMethodValue())) {
				return null; // No implicit match for OPTIONS (we handle it)
			}
			return this;
		}
		return matchRequestMethod(exchange.getRequest().getMethod());
	}
