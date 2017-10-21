	@Test
	public void hashCodeWithSameProfilesReversed() {
		String[] activeProfiles1 = new String[] { "catbert", "dogbert" };
		String[] activeProfiles2 = new String[] { "dogbert", "catbert" };
		MergedContextConfiguration mergedConfig1 = new MergedContextConfiguration(getClass(),
				EMPTY_STRING_ARRAY, EMPTY_CLASS_ARRAY, activeProfiles1, loader);
		MergedContextConfiguration mergedConfig2 = new MergedContextConfiguration(getClass(),
				EMPTY_STRING_ARRAY, EMPTY_CLASS_ARRAY, activeProfiles2, loader);
		assertNotEquals(mergedConfig1.hashCode(), mergedConfig2.hashCode());
	}
