	@Override
	public Mono<ServerResponse> handle(ServerRequest request) {
		HttpMethod method = request.method();
		if (method != null) {
			switch (method) {
				case GET:
					return EntityResponse.fromObject(this.resource).build()
							.map(response -> response);
				case HEAD:
					Resource headResource = new HeadMethodResource(this.resource);
					return EntityResponse.fromObject(headResource).build()
							.map(response -> response);
				case OPTIONS:
					return ServerResponse.ok()
							.allow(SUPPORTED_METHODS)
							.body(BodyInserters.empty());
			}
		}
		return ServerResponse.status(HttpStatus.METHOD_NOT_ALLOWED)
				.allow(SUPPORTED_METHODS)
				.body(BodyInserters.empty());
	}
