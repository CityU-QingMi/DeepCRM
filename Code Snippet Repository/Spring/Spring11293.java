	private Mono<Void> handleResult(HandlerResult handlerResult, BindingContext bindingContext) {
		Object value = handlerResult.getReturnValue();
		if (value != null) {
			ResolvableType type = handlerResult.getReturnType();
			ReactiveAdapter adapter = this.adapterRegistry.getAdapter(type.getRawClass(), value);
			if (isAsyncVoidType(type, adapter)) {
				return Mono.from(adapter.toPublisher(value));
			}
			String name = getAttributeName(handlerResult.getReturnTypeSource());
			bindingContext.getModel().asMap().putIfAbsent(name, value);
		}
		return Mono.empty();
	}
