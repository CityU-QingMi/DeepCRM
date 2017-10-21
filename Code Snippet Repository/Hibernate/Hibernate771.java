	protected String transformEntityName(EntityNaming entityNaming) {
		// prefer the JPA entity name, if specified...
		if ( StringHelper.isNotEmpty( entityNaming.getJpaEntityName() ) ) {
			return entityNaming.getJpaEntityName();
		}
		else {
			// otherwise, use the Hibernate entity name
			return StringHelper.unqualify( entityNaming.getEntityName() );
		}
	}
