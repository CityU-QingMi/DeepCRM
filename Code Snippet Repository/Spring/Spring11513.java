	@Test
	public void lastModified() throws Exception {
		ZonedDateTime now = ZonedDateTime.now();
		Mono<ServerResponse> result = ServerResponse.ok().lastModified(now).build();
		Long expected = now.toInstant().toEpochMilli() / 1000;
		StepVerifier.create(result)
				.expectNextMatches(response -> expected.equals(response.headers().getLastModified() / 1000))
				.expectComplete()
				.verify();
	}
