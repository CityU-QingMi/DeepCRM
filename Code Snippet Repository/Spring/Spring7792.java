	@Override
	@Nullable
	public Object beginTransaction(EntityManager entityManager, TransactionDefinition definition)
			throws PersistenceException, SQLException, TransactionException {

		if (definition.getIsolationLevel() != TransactionDefinition.ISOLATION_DEFAULT) {
			throw new InvalidIsolationLevelException(getClass().getSimpleName() +
					" does not support custom isolation levels due to limitations in standard JPA. " +
					"Specific arrangements may be implemented in custom JpaDialect variants.");
		}
		entityManager.getTransaction().begin();
		return null;
	}
