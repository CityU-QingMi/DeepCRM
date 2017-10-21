	@Test
	public void contentType() throws Exception {
		String body = "foo";
		Mono<EntityResponse<String>>
				result = EntityResponse.fromObject(body).contentType(MediaType.APPLICATION_JSON).build();
		StepVerifier.create(result)
				.expectNextMatches(response -> MediaType.APPLICATION_JSON.equals(response.headers().getContentType()))
				.expectComplete()
				.verify();
	}
