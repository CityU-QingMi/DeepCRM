	static void flush(Session session, boolean synch) throws DataAccessException {
		if (synch) {
			logger.debug("Flushing Hibernate Session on transaction synchronization");
		}
		else {
			logger.debug("Flushing Hibernate Session on explicit request");
		}
		try {
			session.flush();
		}
		catch (HibernateException ex) {
			throw convertHibernateAccessException(ex);
		}
		catch (PersistenceException ex) {
			if (ex.getCause() instanceof HibernateException) {
				throw convertHibernateAccessException((HibernateException) ex.getCause());
			}
			throw ex;
		}

	}
