	@Test
	public void resolveResourceSuccess() throws Exception {
		String versionFile = "bar-version.css";
		String version = "version";
		String file = "bar.css";
		Resource expected = new ClassPathResource("test/" + file, getClass());
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/resources/bar-version.css");
		given(this.chain.resolveResource(request, versionFile, this.locations)).willReturn(null);
		given(this.chain.resolveResource(request, file, this.locations)).willReturn(expected);
		given(this.versionStrategy.extractVersion(versionFile)).willReturn(version);
		given(this.versionStrategy.removeVersion(versionFile, version)).willReturn(file);
		given(this.versionStrategy.getResourceVersion(expected)).willReturn(version);

		this.resolver
				.setStrategyMap(Collections.singletonMap("/**", this.versionStrategy));
		Resource actual = this.resolver.resolveResourceInternal(request, versionFile, this.locations, this.chain);
		assertEquals(expected.getFilename(), actual.getFilename());
		verify(this.versionStrategy, times(1)).getResourceVersion(expected);
		assertThat(actual, instanceOf(HttpResource.class));
		assertEquals("\"" + version + "\"", ((HttpResource)actual).getResponseHeaders().getETag());
	}
