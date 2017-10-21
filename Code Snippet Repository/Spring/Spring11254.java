	@Override
	@Nullable
	public RequestMappingInfo getMatchingCondition(ServerWebExchange exchange) {
		RequestMethodsRequestCondition methods = this.methodsCondition.getMatchingCondition(exchange);
		ParamsRequestCondition params = this.paramsCondition.getMatchingCondition(exchange);
		HeadersRequestCondition headers = this.headersCondition.getMatchingCondition(exchange);
		ConsumesRequestCondition consumes = this.consumesCondition.getMatchingCondition(exchange);
		ProducesRequestCondition produces = this.producesCondition.getMatchingCondition(exchange);

		if (methods == null || params == null || headers == null || consumes == null || produces == null) {
			return null;
		}

		PatternsRequestCondition patterns = this.patternsCondition.getMatchingCondition(exchange);
		if (patterns == null) {
			return null;
		}

		RequestConditionHolder custom = this.customConditionHolder.getMatchingCondition(exchange);
		if (custom == null) {
			return null;
		}

		return new RequestMappingInfo(this.name, patterns,
				methods, params, headers, consumes, produces, custom.getCondition());
	}
