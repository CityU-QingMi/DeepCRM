	@Override
	public Object resolveArgumentValue(MethodParameter methodParameter, BindingContext context,
			ServerWebExchange exchange) {

		Class<?> paramType = methodParameter.getParameterType();
		if (ServerWebExchange.class.isAssignableFrom(paramType)) {
			return exchange;
		}
		else if (ServerHttpRequest.class.isAssignableFrom(paramType)) {
			return exchange.getRequest();
		}
		else if (ServerHttpResponse.class.isAssignableFrom(paramType)) {
			return exchange.getResponse();
		}
		else if (HttpMethod.class == paramType) {
			return exchange.getRequest().getMethod();
		}
		else if (Locale.class == paramType) {
			return exchange.getLocaleContext().getLocale();
		}
		else if (TimeZone.class == paramType) {
			LocaleContext localeContext = exchange.getLocaleContext();
			TimeZone timeZone = getTimeZone(localeContext);
			return timeZone != null ? timeZone : TimeZone.getDefault();
		}
		else if (ZoneId.class == paramType) {
			LocaleContext localeContext = exchange.getLocaleContext();
			TimeZone timeZone = getTimeZone(localeContext);
			return timeZone != null ? timeZone.toZoneId() : ZoneId.systemDefault();
		}
		else if (UriBuilder.class == paramType || UriComponentsBuilder.class == paramType) {
			return UriComponentsBuilder.fromHttpRequest(exchange.getRequest());
		}
		else {
			// should never happen...
			throw new IllegalArgumentException("Unknown parameter type: " +
					paramType + " in method: " + methodParameter.getMethod());
		}
	}
