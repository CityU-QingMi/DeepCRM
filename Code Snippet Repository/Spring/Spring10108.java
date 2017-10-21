	@Test
	public void decodeMultipleXmlRootElement() throws Exception {
		Flux<DataBuffer> source = Flux.just(stringBuffer(POJO_CHILD));
		Flux<Object> output = this.decoder.decode(source, ResolvableType.forClass(Pojo.class),
				null, Collections.emptyMap());

		StepVerifier.create(output)
				.expectNext(new Pojo("foo", "bar"))
				.expectNext(new Pojo("foofoo", "barbar"))
				.expectComplete()
				.verify();
	}
