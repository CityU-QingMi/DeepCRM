	@Test
	public void resolveResourceExisting() {
		Resource expected = mock(Resource.class);
		this.locations = Collections.singletonList(new ClassPathResource("/META-INF/resources/webjars/", getClass()));
		String file = "foo/2.3/foo.txt";
		given(this.chain.resolveResource(this.request, file, this.locations)).willReturn(expected);

		Resource actual = this.resolver.resolveResource(this.request, file, this.locations, this.chain);

		assertEquals(expected, actual);
		verify(this.chain, times(1)).resolveResource(this.request, file, this.locations);
	}
