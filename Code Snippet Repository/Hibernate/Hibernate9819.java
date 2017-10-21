	public static AuditReader get(EntityManager entityManager) throws AuditException {
		if ( entityManager.getDelegate() instanceof Session ) {
			return get( (Session) entityManager.getDelegate() );
		}

		if ( entityManager.getDelegate() instanceof EntityManager ) {
			return get( (EntityManager) entityManager.getDelegate() );
		}

		throw new AuditException( "Hibernate EntityManager not present!" );
	}
