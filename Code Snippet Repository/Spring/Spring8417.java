	@Test
	public void verifyCacheBehaviorForContextHierarchies() {
		int size = 0;
		int hits = 0;
		int misses = 0;

		// Level 1
		loadCtxAndAssertStats(ClassHierarchyContextHierarchyLevel1TestCase.class, ++size, hits, ++misses);
		loadCtxAndAssertStats(ClassHierarchyContextHierarchyLevel1TestCase.class, size, ++hits, misses);

		// Level 2
		loadCtxAndAssertStats(ClassHierarchyContextHierarchyLevel2TestCase.class, ++size /* L2 */, ++hits /* L1 */,
			++misses /* L2 */);
		loadCtxAndAssertStats(ClassHierarchyContextHierarchyLevel2TestCase.class, size, ++hits /* L2 */, misses);
		loadCtxAndAssertStats(ClassHierarchyContextHierarchyLevel2TestCase.class, size, ++hits /* L2 */, misses);

		// Level 3-A
		loadCtxAndAssertStats(ClassHierarchyContextHierarchyLevel3aTestCase.class, ++size /* L3A */, ++hits /* L2 */,
			++misses /* L3A */);
		loadCtxAndAssertStats(ClassHierarchyContextHierarchyLevel3aTestCase.class, size, ++hits /* L3A */, misses);

		// Level 3-B
		loadCtxAndAssertStats(ClassHierarchyContextHierarchyLevel3bTestCase.class, ++size /* L3B */, ++hits /* L2 */,
			++misses /* L3B */);
		loadCtxAndAssertStats(ClassHierarchyContextHierarchyLevel3bTestCase.class, size, ++hits /* L3B */, misses);
	}
