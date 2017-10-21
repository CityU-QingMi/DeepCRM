	@Override
	public Mono<Void> render(@Nullable Map<String, ?> model, @Nullable MediaType contentType,
			ServerWebExchange exchange) {

		if (logger.isTraceEnabled()) {
			logger.trace("Rendering view with model " + model);
		}

		if (contentType != null) {
			exchange.getResponse().getHeaders().setContentType(contentType);
		}

		return getModelAttributes(model, exchange).flatMap(mergedModel -> {
			// Expose RequestContext?
			if (this.requestContextAttribute != null) {
				mergedModel.put(this.requestContextAttribute, createRequestContext(exchange, mergedModel));
			}
			return renderInternal(mergedModel, contentType, exchange);
		});
	}
