	@Test
	void selectMethodByFullyQualifiedNameForSpockSpec() {
		String spockClassName = "org.example.CalculatorSpec";
		String spockMethodName = "#a plus #b equals #c";
		String spockFullyQualifiedMethodName = spockClassName + "#" + spockMethodName;

		MethodSelector selector = selectMethod(spockFullyQualifiedMethodName);
		assertEquals(spockClassName, selector.getClassName());
		assertEquals(spockMethodName, selector.getMethodName());
		assertEquals("", selector.getMethodParameterTypes());
	}
