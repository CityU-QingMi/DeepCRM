	@Test
	public void encodeAsStream() throws Exception {
		Flux<Pojo> source = Flux.just(
				new Pojo("foo", "bar"),
				new Pojo("foofoo", "barbar"),
				new Pojo("foofoofoo", "barbarbar")
		);
		ResolvableType type = ResolvableType.forClass(Pojo.class);
		MediaType mediaType = new MediaType("application", "stream+x-jackson-smile");
		Flux<DataBuffer> output = this.encoder.encode(source, this.bufferFactory, type, mediaType, emptyMap());

		ObjectMapper mapper = Jackson2ObjectMapperBuilder.smile().build();
		StepVerifier.create(output)
				.consumeNextWith(dataBuffer -> readPojo(mapper, Pojo.class, dataBuffer))
				.consumeNextWith(dataBuffer -> readPojo(mapper, Pojo.class, dataBuffer))
				.consumeNextWith(dataBuffer -> readPojo(mapper, Pojo.class, dataBuffer))
				.verifyComplete();
	}
