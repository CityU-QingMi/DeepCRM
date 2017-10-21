	@Test
	public void subclassPatternMatches() throws Exception {
		assertMatch("org.springframework.core.type.AspectJTypeFilterTests$SomeClassExtendingSomeClass",
				"org.springframework.core.type.AspectJTypeFilterTests.SomeClass+");
		assertMatch("org.springframework.core.type.AspectJTypeFilterTests$SomeClassExtendingSomeClass",
				"*+");
		assertMatch("org.springframework.core.type.AspectJTypeFilterTests$SomeClassExtendingSomeClass",
				"java.lang.Object+");

		assertMatch("org.springframework.core.type.AspectJTypeFilterTests$SomeClassImplementingSomeInterface",
				"org.springframework.core.type.AspectJTypeFilterTests.SomeInterface+");
		assertMatch("org.springframework.core.type.AspectJTypeFilterTests$SomeClassImplementingSomeInterface",
				"*+");
		assertMatch("org.springframework.core.type.AspectJTypeFilterTests$SomeClassImplementingSomeInterface",
				"java.lang.Object+");

		assertMatch("org.springframework.core.type.AspectJTypeFilterTests$SomeClassExtendingSomeClassExtendingSomeClassAndImplemnentingSomeInterface",
				"org.springframework.core.type.AspectJTypeFilterTests.SomeInterface+");
		assertMatch("org.springframework.core.type.AspectJTypeFilterTests$SomeClassExtendingSomeClassExtendingSomeClassAndImplemnentingSomeInterface",
				"org.springframework.core.type.AspectJTypeFilterTests.SomeClassExtendingSomeClass+");
		assertMatch("org.springframework.core.type.AspectJTypeFilterTests$SomeClassExtendingSomeClassExtendingSomeClassAndImplemnentingSomeInterface",
				"org.springframework.core.type.AspectJTypeFilterTests.SomeClass+");
		assertMatch("org.springframework.core.type.AspectJTypeFilterTests$SomeClassExtendingSomeClassExtendingSomeClassAndImplemnentingSomeInterface",
				"*+");
		assertMatch("org.springframework.core.type.AspectJTypeFilterTests$SomeClassExtendingSomeClassExtendingSomeClassAndImplemnentingSomeInterface",
				"java.lang.Object+");
	}
