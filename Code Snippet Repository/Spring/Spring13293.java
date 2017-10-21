	@Test
	public void resolveResourceNoVersionStrategy() throws Exception {
		String file = "missing.css";
		given(this.chain.resolveResource(null, file, this.locations)).willReturn(null);

		this.resolver.setStrategyMap(Collections.emptyMap());
		Resource actual = this.resolver.resolveResourceInternal(null, file, this.locations, this.chain);
		assertNull(actual);
		verify(this.chain, times(1)).resolveResource(null, file, this.locations);
	}
