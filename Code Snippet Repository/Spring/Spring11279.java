	@Override
	public Mono<Object> resolveArgument(
			MethodParameter parameter, BindingContext context, ServerWebExchange exchange) {

		String name = getModelAttributeName(parameter);
		Object errors = context.getModel().asMap().get(BindingResult.MODEL_KEY_PREFIX + name);

		Mono<?> errorsMono;
		if (Mono.class.isAssignableFrom(errors.getClass())) {
			errorsMono = (Mono<?>) errors;
		}
		else if (Errors.class.isAssignableFrom(errors.getClass())) {
			errorsMono = Mono.just(errors);
		}
		else {
			throw new IllegalStateException(
					"Unexpected Errors/BindingResult type: " + errors.getClass().getName());
		}

		return errorsMono.cast(Object.class);
	}
