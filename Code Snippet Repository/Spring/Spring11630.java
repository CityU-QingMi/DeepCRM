	@Test
	public void resolveUrlWebJarResourceNotFound() {
		String file = "something/something.js";
		given(this.chain.resolveUrlPath(file, this.locations)).willReturn(Mono.empty());

		String actual = this.resolver.resolveUrlPath(file, this.locations, this.chain).block(TIMEOUT);

		assertNull(actual);
		verify(this.chain, times(1)).resolveUrlPath(file, this.locations);
		verify(this.chain, never()).resolveUrlPath(null, this.locations);
	}
