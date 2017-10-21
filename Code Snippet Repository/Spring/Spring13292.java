	@Test
	public void resolveResourceExisting() throws Exception {
		String file = "bar.css";
		Resource expected = new ClassPathResource("test/" + file, getClass());
		given(this.chain.resolveResource(null, file, this.locations)).willReturn(expected);

		this.resolver.setStrategyMap(Collections.singletonMap("/**", this.versionStrategy));
		Resource actual = this.resolver.resolveResourceInternal(null, file, this.locations, this.chain);
		assertEquals(expected, actual);
		verify(this.chain, times(1)).resolveResource(null, file, this.locations);
		verify(this.versionStrategy, never()).extractVersion(file);
	}
