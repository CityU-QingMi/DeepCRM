	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return checkParameterTypeNoReactiveWrapper(parameter,
				type -> ServerWebExchange.class.isAssignableFrom(type) ||
						ServerHttpRequest.class.isAssignableFrom(type) ||
						ServerHttpResponse.class.isAssignableFrom(type) ||
						HttpMethod.class == type ||
						Locale.class == type ||
						TimeZone.class == type ||
						ZoneId.class == type ||
						UriBuilder.class == type || UriComponentsBuilder.class == type);
	}
