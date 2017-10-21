	@Test
	public void partialCapturingPatterns() {
		pathPattern = checkStructure("{foo}abc");
		assertEquals(RegexPathElement.class.getName(), pathPattern.getHeadSection().getClass().getName());
		checkStructure("abc{foo}");
		checkStructure("/abc{foo}");
		checkStructure("{foo}def/");
		checkStructure("/abc{foo}def/");
		checkStructure("{foo}abc{bar}");
		checkStructure("{foo}abc{bar}/");
		checkStructure("/{foo}abc{bar}/");
	}
