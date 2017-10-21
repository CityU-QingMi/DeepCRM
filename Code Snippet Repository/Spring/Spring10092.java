	@Test
	public void decodeToList() throws Exception {
		ObjectMapper mapper = Jackson2ObjectMapperBuilder.smile().build();
		List<Pojo> list = asList(new Pojo("f1", "b1"), new Pojo("f2", "b2"));
		byte[] serializedList = mapper.writer().writeValueAsBytes(list);
		Flux<DataBuffer> source = Flux.just(this.bufferFactory.wrap(serializedList));

		ResolvableType elementType = ResolvableType.forClassWithGenerics(List.class, Pojo.class);
		Mono<Object> mono = decoder.decodeToMono(source, elementType, null, emptyMap());

		StepVerifier.create(mono)
				.expectNext(list)
				.expectComplete()
				.verify();
	}
