	@Override
	public <T, P extends Publisher<T>> Mono<ServerResponse> body(P publisher, Class<T> elementClass) {
		Assert.notNull(publisher, "'publisher' must not be null");
		Assert.notNull(elementClass, "'elementClass' must not be null");

		return new DefaultEntityResponseBuilder<>(publisher,
				BodyInserters.fromPublisher(publisher, elementClass))
				.headers(this.headers)
				.status(this.statusCode)
				.build()
				.map(entityResponse -> entityResponse);
	}
