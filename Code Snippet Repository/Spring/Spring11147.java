	@Override
	public Mono<ServerResponse> render(String name, Object... modelAttributes) {
		Assert.hasLength(name, "'name' must not be empty");

		return new DefaultRenderingResponseBuilder(name)
				.headers(this.headers)
				.status(this.statusCode)
				.modelAttributes(modelAttributes)
				.build()
				.map(renderingResponse -> renderingResponse);
	}
