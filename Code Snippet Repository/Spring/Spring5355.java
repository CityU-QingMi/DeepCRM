	@Test
	public void namePatternMatches() throws Exception {
		assertMatch("org.springframework.core.type.AspectJTypeFilterTests$SomeClass",
				"org.springframework.core.type.AspectJTypeFilterTests.SomeClass");
		assertMatch("org.springframework.core.type.AspectJTypeFilterTests$SomeClass",
				"*");
		assertMatch("org.springframework.core.type.AspectJTypeFilterTests$SomeClass",
				"*..SomeClass");
		assertMatch("org.springframework.core.type.AspectJTypeFilterTests$SomeClass",
				"org..SomeClass");
	}
