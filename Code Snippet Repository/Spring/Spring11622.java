	@Test
	public void resolveResourceNoVersionInPath() throws Exception {
		String file = "bar.css";
		given(this.chain.resolveResource(null, file, this.locations)).willReturn(Mono.empty());
		given(this.versionStrategy.extractVersion(file)).willReturn("");

		this.resolver.setStrategyMap(Collections.singletonMap("/**", this.versionStrategy));
		Resource actual = this.resolver
				.resolveResourceInternal(null, file, this.locations, this.chain)
				.block(Duration.ofMillis(5000));

		assertNull(actual);
		verify(this.chain, times(1)).resolveResource(null, file, this.locations);
		verify(this.versionStrategy, times(1)).extractVersion(file);
	}
