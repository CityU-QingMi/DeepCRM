	@Test
	public void toMonoWithEmptyBodyAndNoContentType() throws Exception {
		BodyExtractor<Mono<Map<String, String>>, ReactiveHttpInputMessage> extractor =
				BodyExtractors.toMono(new ParameterizedTypeReference<Map<String, String>>() {});

		MockServerHttpRequest request = MockServerHttpRequest.post("/").body(Flux.empty());
		Mono<Map<String, String>> result = extractor.extract(request, this.context);

		StepVerifier.create(result).expectComplete().verify();
	}
