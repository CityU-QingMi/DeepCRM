	@Test
	void selectMethodByClassAndNameForSpockSpec() {
		String spockClassName = "org.example.CalculatorSpec";
		String spockMethodName = "#a plus #b equals #c";

		MethodSelector selector = selectMethod(spockClassName, spockMethodName);
		assertEquals(spockClassName, selector.getClassName());
		assertEquals(spockMethodName, selector.getMethodName());
		assertEquals("", selector.getMethodParameterTypes());
	}
