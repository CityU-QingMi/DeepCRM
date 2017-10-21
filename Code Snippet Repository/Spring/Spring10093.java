	@Test
	public void decodeToFlux() throws Exception {
		ObjectMapper mapper = Jackson2ObjectMapperBuilder.smile().build();
		List<Pojo> list = asList(new Pojo("f1", "b1"), new Pojo("f2", "b2"));
		byte[] serializedList = mapper.writer().writeValueAsBytes(list);
		Flux<DataBuffer> source = Flux.just(this.bufferFactory.wrap(serializedList));

		ResolvableType elementType = forClass(Pojo.class);
		Flux<Object> flux = decoder.decode(source, elementType, null, emptyMap());

		StepVerifier.create(flux)
				.expectNext(new Pojo("f1", "b1"))
				.expectNext(new Pojo("f2", "b2"))
				.verifyComplete();
	}
