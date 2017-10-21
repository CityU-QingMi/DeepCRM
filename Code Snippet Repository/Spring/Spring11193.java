	@Override
	public Mono<Resource> resolveResource(@Nullable ServerWebExchange exchange, String requestPath,
			List<? extends Resource> locations) {

		ResourceResolver resolver = getNext();
		if (resolver == null) {
			return Mono.empty();
		}

		try {
			return resolver.resolveResource(exchange, requestPath, locations, this);
		}
		finally {
			this.index--;
		}
	}
