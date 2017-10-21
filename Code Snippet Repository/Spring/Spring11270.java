	private Mono<Object> getDefaultValue(NamedValueInfo namedValueInfo, MethodParameter parameter,
			BindingContext bindingContext, Model model, ServerWebExchange exchange) {

		Object value = null;
		try {
			if (namedValueInfo.defaultValue != null) {
				value = resolveStringValue(namedValueInfo.defaultValue);
			}
			else if (namedValueInfo.required && !parameter.isOptional()) {
				handleMissingValue(namedValueInfo.name, parameter, exchange);
			}
			value = handleNullValue(namedValueInfo.name, value, parameter.getNestedParameterType());
			value = applyConversion(value, namedValueInfo, parameter, bindingContext, exchange);
			handleResolvedValue(value, namedValueInfo.name, parameter, model, exchange);
			return Mono.justOrEmpty(value);
		}
		catch (Throwable ex) {
			return Mono.error(ex);
		}
	}
