	@Override
	protected Mono<String> resolveUrlPathInternal(String path, List<? extends Resource> locations,
			ResourceResolverChain chain) {

		if (StringUtils.hasText(path)) {
			return getResource(path, locations).map(resource -> path);
		}
		else {
			return Mono.empty();
		}
	}
