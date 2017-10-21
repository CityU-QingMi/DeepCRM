	@Test
	void includeMultipleTags() {
		PostDiscoveryFilter filter = includeTags("tag1", "  tag2  ");

		assertTrue(filter.apply(classWithBothTags).included());
		assertTrue(filter.apply(classWithTag1).included());
		assertTrue(filter.apply(classWithTag1AndSurroundingWhitespace).included());
		assertTrue(filter.apply(classWithTag2).included());

		assertTrue(filter.apply(classWithDifferentTags).excluded());
		assertTrue(filter.apply(classWithNoTags).excluded());
	}
