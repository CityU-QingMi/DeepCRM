	private Mono<? extends Resource> transform(String content, Resource resource,
			ResourceTransformerChain chain, ServerWebExchange exchange) {

		if (!content.startsWith(MANIFEST_HEADER)) {
			if (logger.isTraceEnabled()) {
				logger.trace("Manifest should start with 'CACHE MANIFEST', skip: " + resource);
			}
			return Mono.just(resource);
		}
		if (logger.isTraceEnabled()) {
			logger.trace("Transforming resource: " + resource);
		}
		return Flux.generate(new LineInfoGenerator(content))
				.concatMap(info -> processLine(info, exchange, resource, chain))
				.reduce(new ByteArrayOutputStream(), (out, line) -> {
					writeToByteArrayOutputStream(out, line + "\n");
					return out;
				})
				.map(out -> {
					String hash = DigestUtils.md5DigestAsHex(out.toByteArray());
					writeToByteArrayOutputStream(out, "\n" + "# Hash: " + hash);
					if (logger.isTraceEnabled()) {
						logger.trace("AppCache file: [" + resource.getFilename()+ "] hash: [" + hash + "]");
					}
					return new TransformedResource(resource, out.toByteArray());
				});
	}
