	@Test
	public void resolveUrlWebJarResource() {
		String file = "underscorejs/underscore.js";
		String expected = "underscorejs/1.8.3/underscore.js";
		given(this.chain.resolveUrlPath(file, this.locations)).willReturn(null);
		given(this.chain.resolveUrlPath(expected, this.locations)).willReturn(expected);

		String actual = this.resolver.resolveUrlPath(file, this.locations, this.chain);

		assertEquals(expected, actual);
		verify(this.chain, times(1)).resolveUrlPath(file, this.locations);
		verify(this.chain, times(1)).resolveUrlPath(expected, this.locations);
	}
