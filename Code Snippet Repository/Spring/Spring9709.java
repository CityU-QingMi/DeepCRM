	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

		if (shouldNotFilter(exchange.getRequest())) {
			return chain.filter(exchange);
		}

		if (this.removeOnly) {
			ServerWebExchange withoutForwardHeaders = exchange.mutate()
					.request(builder -> builder.headers(
							headers -> {
								for (String headerName : FORWARDED_HEADER_NAMES) {
									headers.remove(headerName);
								}
							})).build();
			return chain.filter(withoutForwardHeaders);
		}
		else {
			URI uri = UriComponentsBuilder.fromHttpRequest(exchange.getRequest()).build().toUri();
			String prefix = getForwardedPrefix(exchange.getRequest().getHeaders());

			ServerWebExchange withChangedUri = exchange.mutate()
					.request(builder -> {
						builder.uri(uri);
						if (prefix != null) {
							builder.path(prefix + uri.getPath());
							builder.contextPath(prefix);
						}
					}).build();
			return chain.filter(withChangedUri);
		}

	}
