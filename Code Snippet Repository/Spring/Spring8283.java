	private static void assertValueCount(String valueType, final String name,
			MultiValueMap<String, String> map, int count) {

		List<String> values = map.get(name);

		String message = "Expected " + valueType + " <" + name + ">";
		assertNotNull(message, values);

		assertTrue(message + " to have at least <" + count + "> values but found " + values,
				count <= values.size());
	}
