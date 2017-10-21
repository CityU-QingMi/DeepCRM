	@Test
	public void checkResourceWithAllowedLocations() {
		this.resolver.setAllowedLocations(
				new ClassPathResource("test/", PathResourceResolver.class),
				new ClassPathResource("testalternatepath/", PathResourceResolver.class)
		);

		Resource location = new ClassPathResource("test/main.css", PathResourceResolver.class);
		String actual = this.resolver.resolveUrlPath("../testalternatepath/bar.css", Arrays.asList(location), null);
		assertEquals("../testalternatepath/bar.css", actual);
	}
