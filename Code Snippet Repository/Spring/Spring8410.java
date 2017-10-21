	@Test
	public void hashCodeWithSameParent() {
		MergedContextConfiguration parent = new MergedContextConfiguration(getClass(), new String[] { "foo", "bar}" },
				EMPTY_CLASS_ARRAY, EMPTY_STRING_ARRAY, loader);

		MergedContextConfiguration mergedConfig1 = new MergedContextConfiguration(getClass(),
				EMPTY_STRING_ARRAY, EMPTY_CLASS_ARRAY, null, EMPTY_STRING_ARRAY, loader, null, parent);
		MergedContextConfiguration mergedConfig2 = new MergedContextConfiguration(getClass(), EMPTY_STRING_ARRAY,
				EMPTY_CLASS_ARRAY, null, EMPTY_STRING_ARRAY, loader, null, parent);
		assertEquals(mergedConfig1.hashCode(), mergedConfig2.hashCode());
	}
