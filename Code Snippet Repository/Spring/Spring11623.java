	@Test
	public void resolveResourceNoResourceAfterVersionRemoved() throws Exception {
		String versionFile = "bar-version.css";
		String version = "version";
		String file = "bar.css";
		given(this.chain.resolveResource(null, versionFile, this.locations)).willReturn(Mono.empty());
		given(this.chain.resolveResource(null, file, this.locations)).willReturn(Mono.empty());
		given(this.versionStrategy.extractVersion(versionFile)).willReturn(version);
		given(this.versionStrategy.removeVersion(versionFile, version)).willReturn(file);

		this.resolver.setStrategyMap(Collections.singletonMap("/**", this.versionStrategy));
		Resource actual = this.resolver
				.resolveResourceInternal(null, versionFile, this.locations, this.chain)
				.block(Duration.ofMillis(5000));

		assertNull(actual);
		verify(this.versionStrategy, times(1)).removeVersion(versionFile, version);
	}
