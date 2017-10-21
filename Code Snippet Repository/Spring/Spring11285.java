	@Override
	public Mono<Object> resolveArgument(
			MethodParameter parameter, BindingContext context, ServerWebExchange exchange) {

		ResolvableType type = ResolvableType.forMethodParameter(parameter);
		Class<?> resolvedType = type.resolve();
		ReactiveAdapter adapter = (resolvedType != null ? getAdapterRegistry().getAdapter(resolvedType) : null);
		ResolvableType valueType = (adapter != null ? type.getGeneric() : type);

		Assert.state(adapter == null || !adapter.isMultiValue(),
				() -> getClass().getSimpleName() + " does not support multi-value reactive type wrapper: " +
						parameter.getGenericParameterType());

		String name = ModelInitializer.getNameForParameter(parameter);
		Mono<?> valueMono = prepareAttributeMono(name, valueType, context, exchange);

		Map<String, Object> model = context.getModel().asMap();
		MonoProcessor<BindingResult> bindingResultMono = MonoProcessor.create();
		model.put(BindingResult.MODEL_KEY_PREFIX + name, bindingResultMono);

		return valueMono.flatMap(value -> {
			WebExchangeDataBinder binder = context.createDataBinder(exchange, value, name);
			return binder.bind(exchange)
					.doOnError(bindingResultMono::onError)
					.doOnSuccess(aVoid -> {
						validateIfApplicable(binder, parameter);
						BindingResult errors = binder.getBindingResult();
						model.put(BindingResult.MODEL_KEY_PREFIX + name, errors);
						model.put(name, value);
						bindingResultMono.onNext(errors);
					})
					.then(Mono.fromCallable(() -> {
						BindingResult errors = binder.getBindingResult();
						if (adapter != null) {
							return adapter.fromPublisher(errors.hasErrors() ?
									Mono.error(new WebExchangeBindException(parameter, errors)) :
									valueMono);
						}
						else {
							if (errors.hasErrors() && !hasErrorsArgument(parameter)) {
								throw new WebExchangeBindException(parameter, errors);
							}
							return value;
						}
					}));
		});
	}
