	@Test
	void selectMethodByFullyQualifiedNamePreconditions() throws Exception {
		assertThrows(PreconditionViolationException.class, () -> selectMethod(null));
		assertThrows(PreconditionViolationException.class, () -> selectMethod(""));
		assertThrows(PreconditionViolationException.class, () -> selectMethod("   "));
		assertThrows(PreconditionViolationException.class, () -> selectMethod("com.example"));
		assertThrows(PreconditionViolationException.class, () -> selectMethod("com.example.Foo"));
		assertThrows(PreconditionViolationException.class, () -> selectMethod("method"));
		assertThrows(PreconditionViolationException.class, () -> selectMethod("#nonexistentMethod"));
		assertThrows(PreconditionViolationException.class, () -> selectMethod("java.lang.String#"));
		assertThrows(PreconditionViolationException.class, () -> selectMethod("java.lang.String#chars("));
	}
