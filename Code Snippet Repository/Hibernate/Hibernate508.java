	public final void makeEntityManaged() {
		nullifyTransientReferencesIfNotAlready();
		final Object version = Versioning.getVersion( getState(), getPersister() );
		getSession().getPersistenceContext().addEntity(
				getInstance(),
				( getPersister().isMutable() ? Status.MANAGED : Status.READ_ONLY ),
				getState(),
				getEntityKey(),
				version,
				LockMode.WRITE,
				isExecuted,
				getPersister(),
				isVersionIncrementDisabled
		);
	}
