	private Mono<? extends Void> render(List<View> views, Map<String, Object> model,
			ServerWebExchange exchange) {

		for (View view : views) {
			if (view.isRedirectView()) {
				return view.render(model, null, exchange);
			}
		}

		List<MediaType> mediaTypes = getMediaTypes(views);
		MediaType bestMediaType = selectMediaType(exchange, () -> mediaTypes);
		if (bestMediaType != null) {
			for (View view : views) {
				for (MediaType mediaType : view.getSupportedMediaTypes()) {
					if (mediaType.isCompatibleWith(bestMediaType)) {
						return view.render(model, mediaType, exchange);
					}
				}
			}
		}
		throw new NotAcceptableStatusException(mediaTypes);
	}
