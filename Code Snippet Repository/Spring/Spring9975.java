	@Test
	public void fromEnumToMap() {

		for (HttpStatus status : HttpStatus.values()) {
			int value = status.value();
			if (value == 302 || value == 413 || value == 414) {
				continue;
			}
			assertTrue("Map has no value for [" + value + "]", statusCodes.containsKey(value));
			assertEquals("Invalid name for [" + value + "]", statusCodes.get(value), status.name());
		}
	}
