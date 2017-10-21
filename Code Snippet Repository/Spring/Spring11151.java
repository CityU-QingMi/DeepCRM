	@Override
	public Mono<Resource> apply(ServerRequest request) {
		PathContainer pathContainer = request.pathContainer();
		if (!this.pattern.matches(pathContainer)) {
			return Mono.empty();
		}
		pathContainer = this.pattern.extractPathWithinPattern(pathContainer);
		String path = processPath(pathContainer.value());
		if (path.contains("%")) {
			path = StringUtils.uriDecode(path, StandardCharsets.UTF_8);
		}
		if (!StringUtils.hasLength(path) || isInvalidPath(path)) {
			return Mono.empty();
		}
		try {
			Resource resource = this.location.createRelative(path);
			if (resource.exists() && resource.isReadable() && isResourceUnderLocation(resource)) {
				return Mono.just(resource);
			}
			else {
				return Mono.empty();
			}
		}
		catch (IOException ex) {
			throw new UncheckedIOException(ex);
		}
	}
