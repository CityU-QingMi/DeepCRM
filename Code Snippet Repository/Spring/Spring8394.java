	@Test
	public void equalsWithDifferentLocations() {
		String[] locations1 = new String[] { "foo", "bar}" };
		String[] locations2 = new String[] { "baz", "quux}" };
		MergedContextConfiguration mergedConfig1 = new MergedContextConfiguration(getClass(),
				locations1, EMPTY_CLASS_ARRAY, EMPTY_STRING_ARRAY, loader);
		MergedContextConfiguration mergedConfig2 = new MergedContextConfiguration(getClass(),
				locations2, EMPTY_CLASS_ARRAY, EMPTY_STRING_ARRAY, loader);
		assertNotEquals(mergedConfig1, mergedConfig2);
		assertNotEquals(mergedConfig2, mergedConfig1);
	}
