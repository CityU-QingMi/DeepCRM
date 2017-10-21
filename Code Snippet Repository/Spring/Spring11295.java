	@Override
	public Object resolveArgumentValue(MethodParameter methodParameter,
			BindingContext context, ServerWebExchange exchange) {

		Class<?> paramType = methodParameter.getParameterType();
		boolean isMultiValueMap = MultiValueMap.class.isAssignableFrom(paramType);

		HttpHeaders headers = exchange.getRequest().getHeaders();
		return isMultiValueMap ? headers : headers.toSingleValueMap();
	}
