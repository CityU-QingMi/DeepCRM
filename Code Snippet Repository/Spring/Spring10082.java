	@Test
	public void decodeEmptyBodyToMono() throws Exception {
		Flux<DataBuffer> source = Flux.empty();
		ResolvableType elementType = forClass(Pojo.class);
		Mono<Object> mono = new Jackson2JsonDecoder().decodeToMono(source, elementType, null, emptyMap());

		StepVerifier.create(mono)
				.expectNextCount(0)
				.verifyComplete();
	}
