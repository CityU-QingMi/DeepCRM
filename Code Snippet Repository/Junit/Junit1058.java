	@Test
	void excludeMultipleTags() {
		PostDiscoveryFilter filter = excludeTags("tag1", "  tag2  ");

		assertTrue(filter.apply(classWithTag1).excluded());
		assertTrue(filter.apply(classWithTag1AndSurroundingWhitespace).excluded());
		assertTrue(filter.apply(classWithBothTags).excluded());
		assertTrue(filter.apply(classWithTag2).excluded());

		assertTrue(filter.apply(classWithDifferentTags).included());
		assertTrue(filter.apply(classWithNoTags).included());
	}
