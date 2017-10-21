	@Test
	public void resolveResourceVersionDoesNotMatch() throws Exception {
		String versionFile = "bar-version.css";
		String version = "version";
		String file = "bar.css";
		Resource expected = new ClassPathResource("test/" + file, getClass());
		given(this.chain.resolveResource(null, versionFile, this.locations)).willReturn(Mono.empty());
		given(this.chain.resolveResource(null, file, this.locations)).willReturn(Mono.just(expected));
		given(this.versionStrategy.extractVersion(versionFile)).willReturn(version);
		given(this.versionStrategy.removeVersion(versionFile, version)).willReturn(file);
		given(this.versionStrategy.getResourceVersion(expected)).willReturn(Mono.just("newer-version"));

		this.resolver.setStrategyMap(Collections.singletonMap("/**", this.versionStrategy));
		Resource actual = this.resolver
				.resolveResourceInternal(null, versionFile, this.locations, this.chain)
				.block(Duration.ofMillis(5000));

		assertNull(actual);
		verify(this.versionStrategy, times(1)).getResourceVersion(expected);
	}
