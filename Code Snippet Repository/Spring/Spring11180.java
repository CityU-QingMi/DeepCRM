	private Mono<String> processLine(LineInfo info, ServerWebExchange exchange,
			Resource resource, ResourceTransformerChain chain) {

		if (!info.isLink()) {
			return Mono.just(info.getLine());
		}

		String link = toAbsolutePath(info.getLine(), exchange);
		return resolveUrlPath(link, exchange, resource, chain)
				.doOnNext(path -> {
					if (logger.isTraceEnabled()) {
						logger.trace("Link modified: " + path + " (original: " + info.getLine() + ")");
					}
				});
	}
