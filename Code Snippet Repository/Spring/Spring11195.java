	@Override
	public Mono<Resource> transform(ServerWebExchange exchange, Resource resource) {
		ResourceTransformer transformer = getNext();
		if (transformer == null) {
			return Mono.just(resource);
		}
		try {
			return transformer.transform(exchange, resource, this);
		}
		finally {
			this.index--;
		}
	}
