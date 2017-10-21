	@Override
	public Mono<ServerResponse> syncBody(Object body) {
		Assert.notNull(body, "'body' must not be null");
		Assert.isTrue(!(body instanceof Publisher), "Please specify the element class by using " +
				"body(Publisher, Class)");

		return new DefaultEntityResponseBuilder<>(body,
				BodyInserters.fromObject(body))
				.headers(this.headers)
				.status(this.statusCode)
				.build()
				.map(entityResponse -> entityResponse);
	}
