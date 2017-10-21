	@Test
	public void translationNotNeededForTheseExceptions() {
		RepositoryInterfaceImpl target = new StereotypedRepositoryInterfaceImpl();
		RepositoryInterface ri = createProxy(target);

		ri.noThrowsClause();
		ri.throwsPersistenceException();

		target.setBehavior(doNotTranslate);
		try {
			ri.noThrowsClause();
			fail();
		}
		catch (RuntimeException ex) {
			assertSame(doNotTranslate, ex);
		}
		try {
			ri.throwsPersistenceException();
			fail();
		}
		catch (RuntimeException ex) {
			assertSame(doNotTranslate, ex);
		}
	}
