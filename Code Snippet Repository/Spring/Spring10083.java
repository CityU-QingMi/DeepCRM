	@Test
	public void  customDeserializer() {
		DataBuffer buffer = new DefaultDataBufferFactory().wrap("{\"test\": 1}".getBytes());

		Jackson2JsonDecoder decoder = new Jackson2JsonDecoder(new ObjectMapper());
		Flux<TestObject> decoded = decoder.decode(Mono.just(buffer),
				ResolvableType.forClass(TestObject.class), null, null).cast(TestObject.class);

		StepVerifier.create(decoded)
				.assertNext(v -> assertEquals(1, v.getTest()))
				.verifyComplete();
	}
