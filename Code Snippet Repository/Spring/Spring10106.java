	@Test
	public void decodeSingleXmlRootElement() throws Exception {
		Flux<DataBuffer> source = Flux.just(stringBuffer(POJO_ROOT));
		Flux<Object> output = this.decoder.decode(source, ResolvableType.forClass(Pojo.class),
				null, Collections.emptyMap());

		StepVerifier.create(output)
				.expectNext(new Pojo("foofoo", "barbar"))
				.expectComplete()
				.verify();
	}
