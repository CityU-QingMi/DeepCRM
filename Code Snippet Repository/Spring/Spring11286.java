	private Mono<?> prepareAttributeMono(String attributeName, ResolvableType attributeType,
			BindingContext context, ServerWebExchange exchange) {

		Object attribute = context.getModel().asMap().get(attributeName);

		if (attribute == null) {
			attribute = findAndRemoveReactiveAttribute(context.getModel(), attributeName);
		}

		if (attribute == null) {
			Class<?> attributeClass = attributeType.getRawClass();
			Assert.state(attributeClass != null, "No attribute class");
			return createAttribute(attributeName, attributeClass, context, exchange);
		}

		ReactiveAdapter adapterFrom = getAdapterRegistry().getAdapter(null, attribute);
		if (adapterFrom != null) {
			Assert.isTrue(!adapterFrom.isMultiValue(), "Data binding only supports single-value async types");
			return Mono.from(adapterFrom.toPublisher(attribute));
		}
		else {
			return Mono.justOrEmpty(attribute);
		}
	}
