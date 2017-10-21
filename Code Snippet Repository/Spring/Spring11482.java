	@Test
	public void lastModified() throws Exception {
		ZonedDateTime now = ZonedDateTime.now();
		String body = "foo";
		Mono<EntityResponse<String>> result = EntityResponse.fromObject(body).lastModified(now).build();
		Long expected = now.toInstant().toEpochMilli() / 1000;
		StepVerifier.create(result)
				.expectNextMatches(response -> expected.equals(response.headers().getLastModified() / 1000))
				.expectComplete()
				.verify();
	}
