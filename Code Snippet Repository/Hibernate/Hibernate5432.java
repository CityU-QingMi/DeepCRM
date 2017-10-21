	private EntityEntry createEntityEntry() {

		return new MutableEntityEntry(
				// status
				Status.MANAGED,
				// loadedState
				new Object[]{},
				// rowId
				1L,
				// id
				42L,
				// version
				23L,
				// lockMode
				LockMode.OPTIMISTIC,
				// existsInDatabase
				true,
				// persister
				null,
				// disableVersionIncrement
				true,
				getPersistenceContextMock()
		);
	}
