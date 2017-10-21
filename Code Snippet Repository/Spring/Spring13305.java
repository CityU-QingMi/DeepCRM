	@Test
	public void resolveResourceNotFound() {
		String file = "something/something.js";
		given(this.chain.resolveUrlPath(file, this.locations)).willReturn(null);

		Resource actual = this.resolver.resolveResource(this.request, file, this.locations, this.chain);

		assertNull(actual);
		verify(this.chain, times(1)).resolveResource(this.request, file, this.locations);
		verify(this.chain, never()).resolveResource(this.request, null, this.locations);
	}
