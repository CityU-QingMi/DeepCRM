	public static void closeEntityManager(@Nullable EntityManager em) {
		if (em != null) {
			logger.debug("Closing JPA EntityManager");
			try {
				if (em.isOpen()) {
					em.close();
				}
			}
			catch (PersistenceException ex) {
				logger.debug("Could not close JPA EntityManager", ex);
			}
			catch (Throwable ex) {
				logger.debug("Unexpected exception on closing JPA EntityManager", ex);
			}
		}
	}
