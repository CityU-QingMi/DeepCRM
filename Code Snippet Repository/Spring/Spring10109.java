	@Test
	public void decodeMultipleXmlTypeElement() throws Exception {
		Flux<DataBuffer> source = Flux.just(stringBuffer(POJO_CHILD));
		Flux<Object> output = this.decoder.decode(source, ResolvableType.forClass(TypePojo.class),
				null, Collections.emptyMap());

		StepVerifier.create(output)
				.expectNext(new TypePojo("foo", "bar"))
				.expectNext(new TypePojo("foofoo", "barbar"))
				.expectComplete()
				.verify();
	}
