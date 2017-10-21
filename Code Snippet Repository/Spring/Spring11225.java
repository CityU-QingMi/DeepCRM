	@Override
	public ConsumesRequestCondition getMatchingCondition(ServerWebExchange exchange) {
		if (CorsUtils.isPreFlightRequest(exchange.getRequest())) {
			return PRE_FLIGHT_MATCH;
		}
		if (isEmpty()) {
			return this;
		}
		Set<ConsumeMediaTypeExpression> result = new LinkedHashSet<>(expressions);
		for (Iterator<ConsumeMediaTypeExpression> iterator = result.iterator(); iterator.hasNext();) {
			ConsumeMediaTypeExpression expression = iterator.next();
			if (!expression.match(exchange)) {
				iterator.remove();
			}
		}
		return (result.isEmpty()) ? null : new ConsumesRequestCondition(result);
	}
