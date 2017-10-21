	@Test
	void findAllClassesInPackageDelegates() {
		assertThrows(PreconditionViolationException.class,
			() -> ReflectionUtils.findAllClassesInPackage("void.return.null", allTypes, allNames));
		assertThrows(PreconditionViolationException.class,
			() -> ReflectionSupport.findAllClassesInPackage("void.return.null", allTypes, allNames));
		assertNotEquals(0, ReflectionSupport.findAllClassesInPackage("org.junit", allTypes, allNames).size());
		assertEquals(ReflectionUtils.findAllClassesInPackage("org.junit", allTypes, allNames),
			ReflectionSupport.findAllClassesInPackage("org.junit", allTypes, allNames));
	}
