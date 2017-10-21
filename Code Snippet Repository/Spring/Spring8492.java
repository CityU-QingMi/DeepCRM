	private void assertContextConfigEntriesAreNotUnique(Class<?> testClass) {
		try {
			buildContextHierarchyMap(testClass);
			fail("Should throw an IllegalStateException");
		}
		catch (IllegalStateException e) {
			String msg = String.format(
				"The @ContextConfiguration elements configured via @ContextHierarchy in test class [%s] and its superclasses must define unique contexts per hierarchy level.",
				testClass.getName());
			assertEquals(msg, e.getMessage());
		}
	}
