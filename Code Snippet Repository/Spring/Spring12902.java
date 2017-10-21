	@Test
	public void resolveArgument() throws Exception {
		MultiValueMap<String, String> params = getMatrixVariables("cars");
		params.add("colors", "red");
		params.add("colors", "green");
		params.add("colors", "blue");
		params.add("year", "2012");

		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) this.resolver.resolveArgument(
				this.paramMap, this.mavContainer, this.webRequest, null);

		assertEquals("red", map.get("colors"));

		@SuppressWarnings("unchecked")
		MultiValueMap<String, String> multivalueMap = (MultiValueMap<String, String>) this.resolver.resolveArgument(
				this.paramMultivalueMap, this.mavContainer, this.webRequest, null);

		assertEquals(Arrays.asList("red", "green", "blue"), multivalueMap.get("colors"));
	}
