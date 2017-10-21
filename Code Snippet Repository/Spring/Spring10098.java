	private void testTokenize(List<String> source, List<String> expected) {
		Flux<DataBuffer> sourceFlux = Flux.fromIterable(source)
				.map(this::stringBuffer);

		Flux<String> result = sourceFlux
				.flatMap(this.tokenizer)
				.map(tokenBuffer -> {
					try {
						TreeNode root = this.objectMapper.readTree(tokenBuffer.asParser());
						return this.objectMapper.writeValueAsString(root);
					}
					catch (IOException ex) {
						throw new UncheckedIOException(ex);
					}
				});

		StepVerifier.FirstStep<String> builder = StepVerifier.create(result);
		for (String s : expected) {
			builder.assertNext(new JSONAssertConsumer(s));
		}
		builder.verifyComplete();
	}
