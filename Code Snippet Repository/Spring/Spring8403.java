	@Test
	public void equalsWithDifferentParents() {
		MergedContextConfiguration parent1 = new MergedContextConfiguration(getClass(), new String[] { "foo", "bar}" },
			EMPTY_CLASS_ARRAY, EMPTY_STRING_ARRAY, loader);
		MergedContextConfiguration parent2 = new MergedContextConfiguration(getClass(), new String[] { "baz", "quux" },
			EMPTY_CLASS_ARRAY, EMPTY_STRING_ARRAY, loader);

		MergedContextConfiguration mergedConfig1 = new MergedContextConfiguration(getClass(), EMPTY_STRING_ARRAY,
			EMPTY_CLASS_ARRAY, null, EMPTY_STRING_ARRAY, loader, null, parent1);
		MergedContextConfiguration mergedConfig2 = new MergedContextConfiguration(getClass(), EMPTY_STRING_ARRAY,
			EMPTY_CLASS_ARRAY, null, EMPTY_STRING_ARRAY, loader, null, parent2);
		assertNotEquals(mergedConfig1, mergedConfig2);
		assertNotEquals(mergedConfig2, mergedConfig1);
	}
