	@Test
	public void removeContextHierarchyCacheLevel1WithExhaustiveMode() {

		// Load Level 3-A
		TestContext testContext3a = TestContextTestUtils.buildTestContext(
			ClassHierarchyContextHierarchyLevel3aTestCase.class, contextCache);
		testContext3a.getApplicationContext();
		assertContextCacheStatistics(contextCache, "level 3, A", 3, 0, 3);
		assertParentContextCount(2);

		// Load Level 3-B
		TestContext testContext3b = TestContextTestUtils.buildTestContext(
			ClassHierarchyContextHierarchyLevel3bTestCase.class, contextCache);
		testContext3b.getApplicationContext();
		assertContextCacheStatistics(contextCache, "level 3, A and B", 4, 1, 4);
		assertParentContextCount(2);

		// Remove Level 1
		// Should also remove Levels 2, 3-A, and 3-B, leaving nothing.
		contextCache.remove(getMergedContextConfiguration(testContext3a).getParent().getParent(),
			HierarchyMode.EXHAUSTIVE);
		assertContextCacheStatistics(contextCache, "removed level 1", 0, 1, 4);
		assertParentContextCount(0);
	}
