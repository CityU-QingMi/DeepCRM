	@Test
	public void decodeEmptyArrayToFlux() throws Exception {
		Flux<DataBuffer> source = Flux.just(stringBuffer("[]"));
		ResolvableType elementType = forClass(Pojo.class);
		Flux<Object> flux = new Jackson2JsonDecoder().decode(source, elementType, null, emptyMap());

		StepVerifier.create(flux)
				.expectNextCount(0)
				.verifyComplete();
	}
