	@Test
	public void resolveArgumentNoMatch() throws Exception {
		MultiValueMap<String, String> params2 = getMatrixVariables("planes");
		params2.add("colors", "yellow");
		params2.add("colors", "orange");

		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) this.resolver.resolveArgument(
				this.paramMapForPathVar, this.mavContainer, this.webRequest, null);

		assertEquals(Collections.emptyMap(), map);
	}
