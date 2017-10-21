	private void assertSelectMethodByFullyQualifiedName(Class<?> clazz, Method method, String parameterName,
			String expectedParameterTypes) {

		MethodSelector selector = selectMethod(fqmnWithParamNames(parameterName));
		assertEquals(method, selector.getJavaMethod());
		assertEquals(clazz, selector.getJavaClass());
		assertEquals(clazz.getName(), selector.getClassName());
		assertEquals(method.getName(), selector.getMethodName());
		assertEquals(expectedParameterTypes, selector.getMethodParameterTypes());
	}
