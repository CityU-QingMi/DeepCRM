	private void checkNaturalId(
			EntityPersister persister,
			EntityEntry entry,
			Object[] current,
			Object[] loaded,
			SessionImplementor session) {
		if ( persister.hasNaturalIdentifier() && entry.getStatus() != Status.READ_ONLY ) {
			if ( !persister.getEntityMetamodel().hasImmutableNaturalId() ) {
				// SHORT-CUT: if the natural id is mutable (!immutable), no need to do the below checks
				// EARLY EXIT!!!
				return;
			}

			final int[] naturalIdentifierPropertiesIndexes = persister.getNaturalIdentifierProperties();
			final Type[] propertyTypes = persister.getPropertyTypes();
			final boolean[] propertyUpdateability = persister.getPropertyUpdateability();

			final Object[] snapshot = loaded == null
					? session.getPersistenceContext().getNaturalIdSnapshot( entry.getId(), persister )
					: session.getPersistenceContext().getNaturalIdHelper().extractNaturalIdValues( loaded, persister );

			for ( int i = 0; i < naturalIdentifierPropertiesIndexes.length; i++ ) {
				final int naturalIdentifierPropertyIndex = naturalIdentifierPropertiesIndexes[i];
				if ( propertyUpdateability[naturalIdentifierPropertyIndex] ) {
					// if the given natural id property is updatable (mutable), there is nothing to check
					continue;
				}

				final Type propertyType = propertyTypes[naturalIdentifierPropertyIndex];
				if ( !propertyType.isEqual( current[naturalIdentifierPropertyIndex], snapshot[i] ) ) {
					throw new HibernateException(
							String.format(
									"An immutable natural identifier of entity %s was altered from %s to %s",
									persister.getEntityName(),
									propertyTypes[naturalIdentifierPropertyIndex].toLoggableString(
											snapshot[i],
											session.getFactory()
									),
									propertyTypes[naturalIdentifierPropertyIndex].toLoggableString(
											current[naturalIdentifierPropertyIndex],
											session.getFactory()
									)
							)
					);
				}
			}
		}
	}
