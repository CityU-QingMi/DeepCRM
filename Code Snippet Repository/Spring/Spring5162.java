	private void shouldEncodeResourceRegion(Resource resource) {
		ResourceRegion region = new ResourceRegion(resource, 0, 6);
		Flux<DataBuffer> result = this.encoder.encode(Mono.just(region), this.bufferFactory,
				ResolvableType.forClass(ResourceRegion.class),
				MimeTypeUtils.APPLICATION_OCTET_STREAM,
				Collections.emptyMap());

		StepVerifier.create(result)
				.consumeNextWith(stringConsumer("Spring"))
				.expectComplete()
				.verify();
	}
