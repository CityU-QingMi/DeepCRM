	@Test
	public void decodePojo() throws Exception {
		ObjectMapper mapper = Jackson2ObjectMapperBuilder.smile().build();
		Pojo pojo = new Pojo("foo", "bar");
		byte[] serializedPojo = mapper.writer().writeValueAsBytes(pojo);
		
		Flux<DataBuffer> source = Flux.just(this.bufferFactory.wrap(serializedPojo));
		ResolvableType elementType = forClass(Pojo.class);
		Flux<Object> flux = decoder.decode(source, elementType, null, emptyMap());

		StepVerifier.create(flux)
				.expectNext(pojo)
				.verifyComplete();
	}
