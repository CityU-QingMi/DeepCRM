	@Test
	public void decode() throws Exception {
		DataBuffer fooBuffer = stringBuffer("foo");
		DataBuffer barBuffer = stringBuffer("bar");
		Flux<DataBuffer> source = Flux.just(fooBuffer, barBuffer);

		Flux<Resource> result = this.decoder
				.decode(source, ResolvableType.forClass(Resource.class), null, Collections.emptyMap());

		StepVerifier.create(result)
				.consumeNextWith(resource -> {
					try {
						byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
						assertEquals("foobar", new String(bytes));
					}
					catch (IOException e) {
						fail(e.getMessage());
					}
				})
				.expectComplete()
				.verify();
	}
