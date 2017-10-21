	@Test
	public void equalsWithDifferentContextCustomizers() {
		Set<ContextCustomizer> customizers1 = Collections.singleton(mock(ContextCustomizer.class));
		Set<ContextCustomizer> customizers2 = Collections.singleton(mock(ContextCustomizer.class));

		MergedContextConfiguration mergedConfig1 = new MergedContextConfiguration(getClass(), EMPTY_STRING_ARRAY,
			EMPTY_CLASS_ARRAY, null, EMPTY_STRING_ARRAY, null, null, customizers1, loader, null, null);
		MergedContextConfiguration mergedConfig2 = new MergedContextConfiguration(getClass(), EMPTY_STRING_ARRAY,
			EMPTY_CLASS_ARRAY, null, EMPTY_STRING_ARRAY, null, null, customizers2, loader, null, null);
		assertNotEquals(mergedConfig1, mergedConfig2);
		assertNotEquals(mergedConfig2, mergedConfig1);
	}
