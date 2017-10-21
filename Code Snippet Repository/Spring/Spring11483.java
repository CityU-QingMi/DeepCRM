	@Test
	public void cacheControlTag() throws Exception {
		String body = "foo";
		Mono<EntityResponse<String>>
				result = EntityResponse.fromObject(body).cacheControl(CacheControl.noCache()).build();
		StepVerifier.create(result)
				.expectNextMatches(response -> "no-cache".equals(response.headers().getCacheControl()))
				.expectComplete()
				.verify();
	}
