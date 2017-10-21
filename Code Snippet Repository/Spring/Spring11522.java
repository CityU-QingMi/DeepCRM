	@Test
	public void subPath() throws Exception {
		ClassPathResource location = new ClassPathResource("org/springframework/web/reactive/function/server/");

		PathResourceLookupFunction function = new PathResourceLookupFunction("/resources/**", location);
		MockServerRequest request = MockServerRequest.builder()
				.uri(new URI("http://localhost/resources/child/response.txt"))
				.build();
		Mono<Resource> result = function.apply(request);
		File expected = new ClassPathResource("org/springframework/web/reactive/function/server/child/response.txt").getFile();
		StepVerifier.create(result)
				.expectNextMatches(resource -> {
					try {
						return expected.equals(resource.getFile());
					}
					catch (IOException ex) {
						return false;
					}
				})
				.expectComplete()
				.verify();
	}
