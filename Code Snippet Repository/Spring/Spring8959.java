	@Test
	public void noTranslationNeeded() {
		RepositoryInterfaceImpl target = new RepositoryInterfaceImpl();
		RepositoryInterface ri = createProxy(target);

		ri.noThrowsClause();
		ri.throwsPersistenceException();

		target.setBehavior(persistenceException1);
		try {
			ri.noThrowsClause();
			fail();
		}
		catch (RuntimeException ex) {
			assertSame(persistenceException1, ex);
		}
		try {
			ri.throwsPersistenceException();
			fail();
		}
		catch (RuntimeException ex) {
			assertSame(persistenceException1, ex);
		}
	}
