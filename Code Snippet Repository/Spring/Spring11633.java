	@Test
	public void resolveResourceWebJar() {
		this.locations = singletonList(new ClassPathResource("/META-INF/resources/webjars/", getClass()));

		String file = "underscorejs/underscore.js";
		given(this.chain.resolveResource(this.exchange, file, this.locations)).willReturn(Mono.empty());

		Resource expected = mock(Resource.class);
		String expectedPath = "underscorejs/1.8.3/underscore.js";
		given(this.chain.resolveResource(this.exchange, expectedPath, this.locations))
				.willReturn(Mono.just(expected));


		Resource actual = this.resolver
				.resolveResource(this.exchange, file, this.locations, this.chain)
				.block(TIMEOUT);

		assertEquals(expected, actual);
		verify(this.chain, times(1)).resolveResource(this.exchange, file, this.locations);
	}
