	@Test
	public void multipleSeparatorPatterns() {
		pathPattern = checkStructure("///aaa");
		assertEquals(6, pathPattern.getNormalizedLength());
		assertPathElements(pathPattern, SeparatorPathElement.class, SeparatorPathElement.class, SeparatorPathElement.class,
				LiteralPathElement.class);
		pathPattern = checkStructure("///aaa////aaa/b");
		assertEquals(15, pathPattern.getNormalizedLength());
		assertPathElements(pathPattern, SeparatorPathElement.class, SeparatorPathElement.class, SeparatorPathElement.class,
				LiteralPathElement.class, SeparatorPathElement.class,
				SeparatorPathElement.class, SeparatorPathElement.class, SeparatorPathElement.class,
				LiteralPathElement.class, SeparatorPathElement.class, LiteralPathElement.class);
		pathPattern = checkStructure("/////**");
		assertEquals(5, pathPattern.getNormalizedLength());
		assertPathElements(pathPattern, SeparatorPathElement.class, SeparatorPathElement.class,
				SeparatorPathElement.class, SeparatorPathElement.class, WildcardTheRestPathElement.class);
	}
