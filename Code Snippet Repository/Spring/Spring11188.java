	private Mono<? extends Resource> transformContent(String cssContent, Resource resource,
			ResourceTransformerChain chain, ServerWebExchange exchange) {

		List<ContentChunkInfo> contentChunkInfos = parseContent(cssContent);
		if (contentChunkInfos.isEmpty()) {
			if (logger.isTraceEnabled()) {
				logger.trace("No links found.");
			}
			return Mono.just(resource);
		}

		return Flux.fromIterable(contentChunkInfos)
				.concatMap(contentChunkInfo -> {
					String contentChunk = contentChunkInfo.getContent(cssContent);
					if (contentChunkInfo.isLink() && !hasScheme(contentChunk)) {
						String link = toAbsolutePath(contentChunk, exchange);
						return resolveUrlPath(link, exchange, resource, chain).defaultIfEmpty(contentChunk);
					}
					else {
						return Mono.just(contentChunk);
					}
				})
				.reduce(new StringWriter(), (writer, chunk) -> {
					writer.write(chunk);
					return writer;
				})
				.map(writer -> {
					byte[] newContent = writer.toString().getBytes(DEFAULT_CHARSET);
					return new TransformedResource(resource, newContent);
				});
	}
