	private void addCollectionChangeWorkUnit(
			AuditProcess auditProcess, SessionImplementor session,
			String fromEntityName, RelationDescription relDesc, Object value) {
		// relDesc.getToEntityName() doesn't always return the entity name of the value - in case
		// of subclasses, this will be root class, no the actual class. So it can't be used here.
		String toEntityName;
		Serializable id;

		if ( value instanceof HibernateProxy ) {
			final HibernateProxy hibernateProxy = (HibernateProxy) value;
			id = hibernateProxy.getHibernateLazyInitializer().getIdentifier();
			// We've got to initialize the object from the proxy to later read its state.
			value = EntityTools.getTargetFromProxy( session.getFactory(), hibernateProxy );
			// HHH-7249
			// This call must occur after the proxy has been initialized or the returned name will
			// be to the base class which will impact the discriminator value chosen when using an
			// inheritance strategy with discriminators.
			toEntityName = session.bestGuessEntityName( value );
		}
		else {
			toEntityName = session.guessEntityName( value );

			final IdMapper idMapper = enversService.getEntitiesConfigurations().get( toEntityName ).getIdMapper();
			id = (Serializable) idMapper.mapToIdFromEntity( value );
		}

		final Set<String> toPropertyNames = enversService.getEntitiesConfigurations().getToPropertyNames(
				fromEntityName,
				relDesc.getFromPropertyName(),
				toEntityName
		);
		final String toPropertyName = toPropertyNames.iterator().next();

		auditProcess.addWorkUnit(
				new CollectionChangeWorkUnit(
						session,
						toEntityName,
						toPropertyName,
						enversService,
						id,
						value
				)
		);
	}
