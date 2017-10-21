	@Override
	@Nullable
	public Object beginTransaction(EntityManager entityManager, TransactionDefinition definition)
			throws PersistenceException, SQLException, TransactionException {

		if (definition.getIsolationLevel() != TransactionDefinition.ISOLATION_DEFAULT) {
			// Pass custom isolation level on to EclipseLink's DatabaseLogin configuration
			// (since Spring 4.1.2)
			UnitOfWork uow = entityManager.unwrap(UnitOfWork.class);
			uow.getLogin().setTransactionIsolation(definition.getIsolationLevel());
		}

		entityManager.getTransaction().begin();

		if (!definition.isReadOnly() && !this.lazyDatabaseTransaction) {
			// Begin an early transaction to force EclipseLink to get a JDBC Connection
			// so that Spring can manage transactions with JDBC as well as EclipseLink.
			entityManager.unwrap(UnitOfWork.class).beginEarlyTransaction();
		}

		return null;
	}
