	@Test
	public void createMapIsNotTypeSafeForEnumMap() {
		Map<String, Integer> map = createMap(EnumMap.class, Color.class, 3);

		// Use a try-catch block to ensure that the exception is thrown as a result of the
		// next line and not as a result of the previous line.
		try {
			// Note that the 'map' key must be of type String, but the keys in the map
			// returned by createMap() are of type Color. Thus "foo" cannot be cast to a
			// Color.
			map.put("foo", 1);
			fail("Should have thrown a ClassCastException");
		}
		catch (ClassCastException e) {
			/* expected */
		}
	}
