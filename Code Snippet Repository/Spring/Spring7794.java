	@Nullable
	public static EntityManager getTransactionalEntityManager(EntityManagerFactory emf, @Nullable Map<?, ?> properties)
			throws DataAccessResourceFailureException {
		try {
			return doGetTransactionalEntityManager(emf, properties, true);
		}
		catch (PersistenceException ex) {
			throw new DataAccessResourceFailureException("Could not obtain JPA EntityManager", ex);
		}
	}
