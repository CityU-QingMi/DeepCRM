	@SuppressWarnings("")
	public static boolean isNotTransient(String entityName, Object entity, Boolean assumed, SharedSessionContractImplementor session) {
		if ( entity instanceof HibernateProxy ) {
			return true;
		}

		if ( session.getPersistenceContext().isEntryFor( entity ) ) {
			return true;
		}

		// todo : shouldnt assumed be revered here?

		return !isTransient( entityName, entity, assumed, session );
	}
