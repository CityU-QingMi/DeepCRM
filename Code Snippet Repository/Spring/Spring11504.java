	@Test
	public void from() throws Exception {
		ServerResponse other = ServerResponse.ok().header("foo", "bar").build().block();
		Mono<ServerResponse> result = ServerResponse.from(other).build();
		StepVerifier.create(result)
				.expectNextMatches(response -> HttpStatus.OK.equals(response.statusCode()) &&
						"bar".equals(response.headers().getFirst("foo")))
				.expectComplete()
				.verify();
	}
