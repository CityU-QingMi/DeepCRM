	private Mono<?> constructAttribute(Constructor<?> ctor, String attributeName,
			BindingContext context, ServerWebExchange exchange) {

		if (ctor.getParameterCount() == 0) {
			// A single default constructor -> clearly a standard JavaBeans arrangement.
			return Mono.just(BeanUtils.instantiateClass(ctor));
		}

		// A single data class constructor -> resolve constructor arguments from request parameters.
		return WebExchangeDataBinder.extractValuesToBind(exchange).map(bindValues -> {
			ConstructorProperties cp = ctor.getAnnotation(ConstructorProperties.class);
			String[] paramNames = (cp != null ? cp.value() : parameterNameDiscoverer.getParameterNames(ctor));
			Assert.state(paramNames != null, () -> "Cannot resolve parameter names for constructor " + ctor);
			Class<?>[] paramTypes = ctor.getParameterTypes();
			Assert.state(paramNames.length == paramTypes.length,
					() -> "Invalid number of parameter names: " + paramNames.length + " for constructor " + ctor);
			Object[] args = new Object[paramTypes.length];
			WebDataBinder binder = context.createDataBinder(exchange, null, attributeName);
			String fieldDefaultPrefix = binder.getFieldDefaultPrefix();
			String fieldMarkerPrefix = binder.getFieldMarkerPrefix();
			for (int i = 0; i < paramNames.length; i++) {
				String paramName = paramNames[i];
				Class<?> paramType = paramTypes[i];
				Object value = bindValues.get(paramName);
				if (value == null) {
					if (fieldDefaultPrefix != null) {
						value = bindValues.get(fieldDefaultPrefix + paramName);
					}
					if (value == null && fieldMarkerPrefix != null) {
						if (bindValues.get(fieldMarkerPrefix + paramName) != null) {
							value = binder.getEmptyValue(paramType);
						}
					}
				}
				value = (value instanceof List ? ((List<?>) value).toArray() : value);
				MethodParameter methodParam = new MethodParameter(ctor, i);
				if (value == null && methodParam.isOptional()) {
					args[i] = (methodParam.getParameterType() == Optional.class ? Optional.empty() : null);
				}
				else {
					args[i] = binder.convertIfNecessary(value, paramTypes[i], methodParam);
				}
			}
			return BeanUtils.instantiateClass(ctor, args);
		});
	}
