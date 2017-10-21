	@Test
	public void resolveUrlExistingNotInJarFile() {
		this.locations = singletonList(new ClassPathResource("/META-INF/resources/webjars/", getClass()));
		String file = "foo/foo.txt";
		given(this.chain.resolveUrlPath(file, this.locations)).willReturn(Mono.empty());

		String actual = this.resolver.resolveUrlPath(file, this.locations, this.chain).block(TIMEOUT);

		assertNull(actual);
		verify(this.chain, times(1)).resolveUrlPath(file, this.locations);
		verify(this.chain, never()).resolveUrlPath("foo/2.3/foo.txt", this.locations);
	}
