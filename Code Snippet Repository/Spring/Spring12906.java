	@Test
	public void resolveArgument() throws Exception {
		MultiValueMap<String, String> params = getMatrixVariables("cars");
		params.add("colors", "red");
		params.add("colors", "green");
		params.add("colors", "blue");

		assertEquals(Arrays.asList("red", "green", "blue"),
				this.resolver.resolveArgument(this.paramColors, this.mavContainer, this.webRequest, null));
	}
