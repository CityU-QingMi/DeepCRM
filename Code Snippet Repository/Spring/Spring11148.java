	@Override
	public Mono<ServerResponse> render(String name, Map<String, ?> model) {
		Assert.hasLength(name, "'name' must not be empty");

		return new DefaultRenderingResponseBuilder(name)
				.headers(this.headers)
				.status(this.statusCode)
				.modelAttributes(model)
				.build()
				.map(renderingResponse -> renderingResponse);
	}
