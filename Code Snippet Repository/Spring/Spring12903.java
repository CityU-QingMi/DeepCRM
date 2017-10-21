	@Test
	public void resolveArgumentPathVariable() throws Exception {
		MultiValueMap<String, String> params1 = getMatrixVariables("cars");
		params1.add("colors", "red");
		params1.add("colors", "purple");

		MultiValueMap<String, String> params2 = getMatrixVariables("planes");
		params2.add("colors", "yellow");
		params2.add("colors", "orange");

		@SuppressWarnings("unchecked")
		Map<String, String> mapForPathVar = (Map<String, String>) this.resolver.resolveArgument(
				this.paramMapForPathVar, this.mavContainer, this.webRequest, null);

		assertEquals(Arrays.asList("red", "purple"), mapForPathVar.get("colors"));

		@SuppressWarnings("unchecked")
		Map<String, String> mapAll = (Map<String, String>) this.resolver.resolveArgument(
				this.paramMap, this.mavContainer, this.webRequest, null);

		assertEquals("red", mapAll.get("colors"));
	}
