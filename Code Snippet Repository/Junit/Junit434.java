	@Test
	void defaultDisplayNamesForTestClasses() {
		ClassTestDescriptor descriptor = new ClassTestDescriptor(uniqueId, getClass());
		assertEquals(getClass().getSimpleName(), descriptor.getDisplayName());

		descriptor = new NestedClassTestDescriptor(uniqueId, NestedTestCase.class);
		assertEquals(NestedTestCase.class.getSimpleName(), descriptor.getDisplayName());

		descriptor = new ClassTestDescriptor(uniqueId, StaticTestCase.class);
		String staticDisplayName = getClass().getSimpleName() + "$" + StaticTestCase.class.getSimpleName();
		assertEquals(staticDisplayName, descriptor.getDisplayName());

		descriptor = new ClassTestDescriptor(uniqueId, StaticTestCaseLevel2.class);
		staticDisplayName += "$" + StaticTestCaseLevel2.class.getSimpleName();
		assertEquals(staticDisplayName, descriptor.getDisplayName());
	}
