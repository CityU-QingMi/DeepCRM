	@Test
	public void resolveResourceSuccess() throws Exception {
		String versionFile = "bar-version.css";
		String version = "version";
		String file = "bar.css";
		Resource expected = new ClassPathResource("test/" + file, getClass());
		MockServerHttpRequest request = MockServerHttpRequest.get("/resources/bar-version.css").build();
		ServerWebExchange exchange = MockServerWebExchange.from(request);
		given(this.chain.resolveResource(exchange, versionFile, this.locations)).willReturn(Mono.empty());
		given(this.chain.resolveResource(exchange, file, this.locations)).willReturn(Mono.just(expected));
		given(this.versionStrategy.extractVersion(versionFile)).willReturn(version);
		given(this.versionStrategy.removeVersion(versionFile, version)).willReturn(file);
		given(this.versionStrategy.getResourceVersion(expected)).willReturn(Mono.just(version));

		this.resolver.setStrategyMap(Collections.singletonMap("/**", this.versionStrategy));
		Resource actual = this.resolver
				.resolveResourceInternal(exchange, versionFile, this.locations, this.chain)
				.block(Duration.ofMillis(5000));

		assertEquals(expected.getFilename(), actual.getFilename());
		verify(this.versionStrategy, times(1)).getResourceVersion(expected);
		assertThat(actual, instanceOf(HttpResource.class));
		assertEquals("\"" + version + "\"", ((HttpResource)actual).getResponseHeaders().getETag());
	}
