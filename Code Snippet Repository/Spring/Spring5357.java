	@Test
	public void annotationPatternMatches() throws Exception {
		assertMatch("org.springframework.core.type.AspectJTypeFilterTests$SomeClassAnnotatedWithComponent",
				"@org.springframework.stereotype.Component *..*");
		assertMatch("org.springframework.core.type.AspectJTypeFilterTests$SomeClassAnnotatedWithComponent",
				"@* *..*");
		assertMatch("org.springframework.core.type.AspectJTypeFilterTests$SomeClassAnnotatedWithComponent",
				"@*..* *..*");
		assertMatch("org.springframework.core.type.AspectJTypeFilterTests$SomeClassAnnotatedWithComponent",
				"@*..*Component *..*");
		assertMatch("org.springframework.core.type.AspectJTypeFilterTests$SomeClassAnnotatedWithComponent",
				"@org.springframework.stereotype.Component *..*Component");
		assertMatch("org.springframework.core.type.AspectJTypeFilterTests$SomeClassAnnotatedWithComponent",
				"@org.springframework.stereotype.Component *");
	}
