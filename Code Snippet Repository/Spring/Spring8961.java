	private void doTestTranslationNeededForTheseExceptions(RepositoryInterfaceImpl target) {
		RepositoryInterface ri = createProxy(target);

		target.setBehavior(persistenceException1);
		try {
			ri.noThrowsClause();
			fail();
		}
		catch (DataAccessException ex) {
			// Expected
			assertSame(persistenceException1, ex.getCause());
		}
		catch (PersistenceException ex) {
			fail("Should have been translated");
		}

		try {
			ri.throwsPersistenceException();
			fail();
		}
		catch (PersistenceException ex) {
			assertSame(persistenceException1, ex);
		}
	}
