	@Test
	public void resolveResourceNotFound() {
		String file = "something/something.js";
		given(this.chain.resolveResource(this.exchange, file, this.locations)).willReturn(Mono.empty());

		Resource actual = this.resolver
				.resolveResource(this.exchange, file, this.locations, this.chain)
				.block(TIMEOUT);

		assertNull(actual);
		verify(this.chain, times(1)).resolveResource(this.exchange, file, this.locations);
		verify(this.chain, never()).resolveResource(this.exchange, null, this.locations);
	}
