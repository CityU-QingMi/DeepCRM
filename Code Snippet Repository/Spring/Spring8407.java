	@Test
	public void hashCodeWithDifferentProfiles() {
		String[] activeProfiles1 = new String[] { "catbert", "dogbert" };
		String[] activeProfiles2 = new String[] { "X", "Y" };
		MergedContextConfiguration mergedConfig1 = new MergedContextConfiguration(getClass(),
				EMPTY_STRING_ARRAY, EMPTY_CLASS_ARRAY, activeProfiles1, loader);
		MergedContextConfiguration mergedConfig2 = new MergedContextConfiguration(getClass(),
				EMPTY_STRING_ARRAY, EMPTY_CLASS_ARRAY, activeProfiles2, loader);
		assertNotEquals(mergedConfig1.hashCode(), mergedConfig2.hashCode());
	}
