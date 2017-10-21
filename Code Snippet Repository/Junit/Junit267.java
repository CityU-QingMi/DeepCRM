	@Test
	void assertIterableEqualsNestedIterablesOfMixedSubtypes() {
		assertIterableEquals(
			listOf(1, 2, listOf(3, setOf(4, 5), setOf(6L), listOf(listOf(setOf(7)))), setOf(8), listOf(setOf(9L))),
			listOf(1, 2, listOf(3, setOf(4, 5), setOf(6L), listOf(listOf(setOf(7)))), setOf(8), listOf(setOf(9L))));

		assertIterableEquals(
			listOf("a", setOf('b', 'c'), setOf((int) 'd'), listOf(listOf(listOf("ef"), listOf(listOf("ghi"))))),
			setOf("a", listOf('b', 'c'), listOf((int) 'd'), setOf(setOf(setOf("ef"), setOf(setOf("ghi"))))));
	}
