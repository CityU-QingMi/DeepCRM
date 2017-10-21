	@Test
	void loadMethodWithInvalidFormatForFullyQualifiedMethodName() {
		assertThrows(PreconditionViolationException.class, () -> ReflectionUtils.loadMethod(null));
		assertThrows(PreconditionViolationException.class, () -> ReflectionUtils.loadMethod(""));
		assertThrows(PreconditionViolationException.class, () -> ReflectionUtils.loadMethod("   "));
		assertThrows(PreconditionViolationException.class, () -> ReflectionUtils.loadMethod("method"));
		assertThrows(PreconditionViolationException.class, () -> ReflectionUtils.loadMethod("#nonexistentMethod"));
		assertThrows(PreconditionViolationException.class, () -> ReflectionUtils.loadMethod("java.lang.String#"));
		assertThrows(PreconditionViolationException.class, () -> ReflectionUtils.loadMethod("java.lang.String#chars("));
		assertThrows(PreconditionViolationException.class, () -> ReflectionUtils.loadMethod("java.lang.String#chars)"));
	}
