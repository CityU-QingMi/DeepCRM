	@Test
	public void decodePojo() throws Exception {
		Flux<DataBuffer> source = Flux.just(stringBuffer("{\"foo\": \"foofoo\", \"bar\": \"barbar\"}"));
		ResolvableType elementType = forClass(Pojo.class);
		Flux<Object> flux = new Jackson2JsonDecoder().decode(source, elementType, null,
				emptyMap());

		StepVerifier.create(flux)
				.expectNext(new Pojo("foofoo", "barbar"))
				.verifyComplete();
	}
