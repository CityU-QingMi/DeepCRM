	@Test
	void selectMethodByClassNameMethodNameAndMethodParameterTypesPreconditions() throws Exception {
		assertThrows(PreconditionViolationException.class, () -> selectMethod("TestClass", null, "int"));
		assertThrows(PreconditionViolationException.class, () -> selectMethod("TestClass", "", "int"));
		assertThrows(PreconditionViolationException.class, () -> selectMethod("TestClass", "  ", "int"));
		assertThrows(PreconditionViolationException.class, () -> selectMethod((String) null, "method", "int"));
		assertThrows(PreconditionViolationException.class, () -> selectMethod("", "method", "int"));
		assertThrows(PreconditionViolationException.class, () -> selectMethod("   ", "method", "int"));
		assertThrows(PreconditionViolationException.class, () -> selectMethod("TestClass", "method", null));
	}
