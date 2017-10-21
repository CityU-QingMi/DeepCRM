	@Test
	public void pathSegment() throws Exception {
		// basic
		testPathSegment("cars", "cars", new LinkedMultiValueMap<>());

		// empty
		testPathSegment("", "", new LinkedMultiValueMap<>());

		// spaces
		testPathSegment("%20%20", "  ", new LinkedMultiValueMap<>());
		testPathSegment("%20a%20", " a ", new LinkedMultiValueMap<>());
	}
