	@Test
	public void decodeNewLine() throws InterruptedException {
		DataBuffer fooBar = stringBuffer("\nfoo\r\nbar\r");
		DataBuffer baz = stringBuffer("\nbaz");
		Flux<DataBuffer> source = Flux.just(fooBar, baz);
		Flux<String> output = decoder.decode(source, ResolvableType.forClass(String.class),
				null, Collections.emptyMap());

		StepVerifier.create(output)
				.expectNext("\n", "foo\r", "\n", "bar\r", "\n", "baz")
				.expectComplete()
				.verify();

	}
