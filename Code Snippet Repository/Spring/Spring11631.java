	@Test
	public void resolveResourceExisting() {
		Resource expected = mock(Resource.class);
		this.locations = singletonList(new ClassPathResource("/META-INF/resources/webjars/", getClass()));
		String file = "foo/2.3/foo.txt";
		given(this.chain.resolveResource(this.exchange, file, this.locations)).willReturn(Mono.just(expected));

		Resource actual = this.resolver
				.resolveResource(this.exchange, file, this.locations, this.chain)
				.block(TIMEOUT);

		assertEquals(expected, actual);
		verify(this.chain, times(1)).resolveResource(this.exchange, file, this.locations);
	}
