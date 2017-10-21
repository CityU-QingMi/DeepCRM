	protected Serializable saveWithGeneratedId(
			Object entity,
			String entityName,
			Object anything,
			EventSource source,
			boolean requiresImmediateIdAccess) {
		if ( entity instanceof SelfDirtinessTracker ) {
			( (SelfDirtinessTracker) entity ).$$_hibernate_clearDirtyAttributes();
		}

		EntityPersister persister = source.getEntityPersister( entityName, entity );
		Serializable generatedId = persister.getIdentifierGenerator().generate( source, entity );
		if ( generatedId == null ) {
			throw new IdentifierGenerationException( "null id generated for:" + entity.getClass() );
		}
		else if ( generatedId == IdentifierGeneratorHelper.SHORT_CIRCUIT_INDICATOR ) {
			return source.getIdentifier( entity );
		}
		else if ( generatedId == IdentifierGeneratorHelper.POST_INSERT_INDICATOR ) {
			return performSave( entity, null, persister, true, anything, source, requiresImmediateIdAccess );
		}
		else {
			// TODO: define toString()s for generators
			if ( LOG.isDebugEnabled() ) {
				LOG.debugf(
						"Generated identifier: %s, using strategy: %s",
						persister.getIdentifierType().toLoggableString( generatedId, source.getFactory() ),
						persister.getIdentifierGenerator().getClass().getName()
				);
			}

			return performSave( entity, generatedId, persister, false, anything, source, true );
		}
	}
