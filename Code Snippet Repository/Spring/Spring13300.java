	@Test
	public void resolveUrlExisting() {
		this.locations = Collections.singletonList(new ClassPathResource("/META-INF/resources/webjars/", getClass()));
		String file = "/foo/2.3/foo.txt";
		given(this.chain.resolveUrlPath(file, this.locations)).willReturn(file);

		String actual = this.resolver.resolveUrlPath(file, this.locations, this.chain);

		assertEquals(file, actual);
		verify(this.chain, times(1)).resolveUrlPath(file, this.locations);
	}
