	@Test
	public void resolveResourceWebJar() {
		Resource expected = mock(Resource.class);
		String file = "underscorejs/underscore.js";
		String expectedPath = "underscorejs/1.8.3/underscore.js";
		this.locations = Collections.singletonList(new ClassPathResource("/META-INF/resources/webjars/", getClass()));
		given(this.chain.resolveResource(this.request, expectedPath, this.locations)).willReturn(expected);

		Resource actual = this.resolver.resolveResource(this.request, file, this.locations, this.chain);

		assertEquals(expected, actual);
		verify(this.chain, times(1)).resolveResource(this.request, file, this.locations);
	}
