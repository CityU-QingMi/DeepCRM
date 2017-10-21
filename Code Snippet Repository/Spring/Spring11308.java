	protected Mono<Map<String, Object>> getModelAttributes(@Nullable Map<String, ?> model,
			ServerWebExchange exchange) {

		int size = (model != null ? model.size() : 0);

		Map<String, Object> attributes = new LinkedHashMap<>(size);
		if (model != null) {
			attributes.putAll(model);
		}

		return resolveAsyncAttributes(attributes).then(Mono.just(attributes));
	}
