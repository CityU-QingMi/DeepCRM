	@Test
	public void createMapIsNotTypeSafeForLinkedMultiValueMap() {
		Map<String, Integer> map = createMap(MultiValueMap.class, null, 3);

		// Use a try-catch block to ensure that the exception is thrown as a result of the
		// next line and not as a result of the previous line.
		try {
			// Note: 'map' values must be of type Integer, but the values in the map
			// returned by createMap() are of type java.util.List. Thus 1 cannot be
			// cast to a List.
			map.put("foo", 1);
			fail("Should have thrown a ClassCastException");
		}
		catch (ClassCastException e) {
			/* expected */
		}
	}
