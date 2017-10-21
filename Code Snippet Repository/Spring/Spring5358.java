	@Test
	public void compositionPatternMatches() throws Exception {
		assertMatch("org.springframework.core.type.AspectJTypeFilterTests$SomeClass",
				"!*..SomeOtherClass");
		assertMatch("org.springframework.core.type.AspectJTypeFilterTests$SomeClassExtendingSomeClassExtendingSomeClassAndImplemnentingSomeInterface",
				"org.springframework.core.type.AspectJTypeFilterTests.SomeInterface+ " +
						"&& org.springframework.core.type.AspectJTypeFilterTests.SomeClass+ " +
						"&& org.springframework.core.type.AspectJTypeFilterTests.SomeClassExtendingSomeClass+");
		assertMatch("org.springframework.core.type.AspectJTypeFilterTests$SomeClassExtendingSomeClassExtendingSomeClassAndImplemnentingSomeInterface",
				"org.springframework.core.type.AspectJTypeFilterTests.SomeInterface+ " +
						"|| org.springframework.core.type.AspectJTypeFilterTests.SomeClass+ " +
						"|| org.springframework.core.type.AspectJTypeFilterTests.SomeClassExtendingSomeClass+");
	}
