	@Test
	public void removeContextHierarchyCacheLevel2WithExhaustiveMode() {

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

		// Remove Level 2
		// Should wipe the cache
		contextCache.remove(getMergedContextConfiguration(testContext3a).getParent(), HierarchyMode.EXHAUSTIVE);
		assertContextCacheStatistics(contextCache, "removed level 2", 0, 1, 4);
		assertParentContextCount(0);
	}
