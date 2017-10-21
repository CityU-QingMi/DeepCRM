	@Test
	public void removeContextHierarchyCacheLevel3Then2() {

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

		// Remove Level 3-A
		contextCache.remove(getMergedContextConfiguration(testContext3a), HierarchyMode.CURRENT_LEVEL);
		assertContextCacheStatistics(contextCache, "removed level 3-A", 3, 1, 4);
		assertParentContextCount(2);

		// Remove Level 2
		// Should also remove Level 3-B, leaving only Level 1.
		contextCache.remove(getMergedContextConfiguration(testContext3b).getParent(), HierarchyMode.CURRENT_LEVEL);
		assertContextCacheStatistics(contextCache, "removed level 2", 1, 1, 4);
		assertParentContextCount(0);
	}
