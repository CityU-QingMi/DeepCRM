	@Test
	public void hashCodeWithDifferentConfigClasses() {
		Class<?>[] classes1 = new Class<?>[] { String.class, Integer.class };
		Class<?>[] classes2 = new Class<?>[] { Boolean.class, Number.class };
		MergedContextConfiguration mergedConfig1 = new MergedContextConfiguration(getClass(),
				EMPTY_STRING_ARRAY, classes1, EMPTY_STRING_ARRAY, loader);
		MergedContextConfiguration mergedConfig2 = new MergedContextConfiguration(getClass(),
				EMPTY_STRING_ARRAY, classes2, EMPTY_STRING_ARRAY, loader);
		assertNotEquals(mergedConfig1.hashCode(), mergedConfig2.hashCode());
	}
