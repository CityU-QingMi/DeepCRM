	@Test
	public void resolveUrlExisting() {
		this.locations = singletonList(new ClassPathResource("/META-INF/resources/webjars/", getClass()));
		String file = "/foo/2.3/foo.txt";
		given(this.chain.resolveUrlPath(file, this.locations)).willReturn(Mono.just(file));

		String actual = this.resolver.resolveUrlPath(file, this.locations, this.chain).block(TIMEOUT);

		assertEquals(file, actual);
		verify(this.chain, times(1)).resolveUrlPath(file, this.locations);
	}
