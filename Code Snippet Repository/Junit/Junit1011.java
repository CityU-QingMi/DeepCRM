	@Test
	void selectMethodByFullyQualifiedNameForSpockSpecWithParameters() {
		String className = "org.example.CalculatorSpec";
		String methodName = "#a plus #b equals #c";
		String methodParameters = "int, int, int";
		String spockFullyQualifiedMethodName = String.format("%s#%s(%s)", className, methodName, methodParameters);

		MethodSelector selector = selectMethod(spockFullyQualifiedMethodName);
		assertEquals(className, selector.getClassName());
		assertEquals(methodName, selector.getMethodName());
		assertEquals(methodParameters, selector.getMethodParameterTypes());
	}
