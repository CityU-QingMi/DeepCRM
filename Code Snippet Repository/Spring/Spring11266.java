	@Override
	public Mono<Object> resolveArgument(
			MethodParameter parameter, BindingContext bindingContext, ServerWebExchange exchange) {

		NamedValueInfo namedValueInfo = getNamedValueInfo(parameter);
		MethodParameter nestedParameter = parameter.nestedIfOptional();

		Object resolvedName = resolveStringValue(namedValueInfo.name);
		if (resolvedName == null) {
			return Mono.error(new IllegalArgumentException(
					"Specified name must not resolve to null: [" + namedValueInfo.name + "]"));
		}

		Model model = bindingContext.getModel();

		return resolveName(resolvedName.toString(), nestedParameter, exchange)
				.map(arg -> {
					if ("".equals(arg) && namedValueInfo.defaultValue != null) {
						arg = resolveStringValue(namedValueInfo.defaultValue);
					}
					arg = applyConversion(arg, namedValueInfo, parameter, bindingContext, exchange);
					handleResolvedValue(arg, namedValueInfo.name, parameter, model, exchange);
					return arg;
				})
				.switchIfEmpty(getDefaultValue(
						namedValueInfo, parameter, bindingContext, model, exchange));
	}
