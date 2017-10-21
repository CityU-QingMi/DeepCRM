	@Override
	public Mono<String> resolveUrlPath(String resourcePath, List<? extends Resource> locations) {
		ResourceResolver resolver = getNext();
		if (resolver == null) {
			return Mono.empty();
		}

		try {
			return resolver.resolveUrlPath(resourcePath, locations, this);
		}
		finally {
			this.index--;
		}
	}
