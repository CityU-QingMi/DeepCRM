	@Test
	public void encode() throws Exception {
		Flux<Pojo> source = Flux.just(new Pojo("foofoo", "barbar"), new Pojo("foofoofoo", "barbarbar"));
		Flux<DataBuffer> output = this.encoder.encode(source, this.bufferFactory,
				ResolvableType.forClass(Pojo.class),
				MediaType.APPLICATION_XML, Collections.emptyMap());

		StepVerifier.create(output)
				.consumeNextWith(dataBuffer -> {
					try {
						String s = DataBufferTestUtils
								.dumpString(dataBuffer, StandardCharsets.UTF_8);
						assertThat(s, isSimilarTo("<?xml version='1.0' encoding='UTF-8' standalone='yes'?>" +
								"<pojo><bar>barbar</bar><foo>foofoo</foo></pojo>"));
					}
					finally {
						DataBufferUtils.release(dataBuffer);
					}
				})
				.expectComplete()
				.verify();
	}
