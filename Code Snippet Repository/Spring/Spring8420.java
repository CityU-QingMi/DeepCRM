	@Test
	public void removeContextHierarchyCacheLevel2() {

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
		// Should also remove Levels 3-A and 3-B, leaving only Level 1 as a context in the
		// cache but also removing the Level 1 hierarchy since all children have been
		// removed.
		contextCache.remove(getMergedContextConfiguration(testContext3a).getParent(), HierarchyMode.CURRENT_LEVEL);
		assertContextCacheStatistics(contextCache, "removed level 2", 1, 1, 4);
		assertParentContextCount(0);
	}
