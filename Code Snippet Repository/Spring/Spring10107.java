	@Test
	public void decodeSingleXmlTypeElement() throws Exception {
		Flux<DataBuffer> source = Flux.just(stringBuffer(POJO_ROOT));
		Flux<Object> output = this.decoder.decode(source, ResolvableType.forClass(TypePojo.class),
				null, Collections.emptyMap());

		StepVerifier.create(output)
				.expectNext(new TypePojo("foofoo", "barbar"))
				.expectComplete()
				.verify();
	}
