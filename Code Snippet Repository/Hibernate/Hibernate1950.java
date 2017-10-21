	public static NonNullableTransientDependencies findNonNullableTransientEntities(
			String entityName,
			Object entity,
			Object[] values,
			boolean isEarlyInsert,
			SharedSessionContractImplementor session) {
		final Nullifier nullifier = new Nullifier( entity, false, isEarlyInsert, session );
		final EntityPersister persister = session.getEntityPersister( entityName, entity );
		final String[] propertyNames = persister.getPropertyNames();
		final Type[] types = persister.getPropertyTypes();
		final boolean[] nullability = persister.getPropertyNullability();
		final NonNullableTransientDependencies nonNullableTransientEntities = new NonNullableTransientDependencies();
		for ( int i = 0; i < types.length; i++ ) {
			collectNonNullableTransientEntities(
					nullifier,
					values[i],
					propertyNames[i],
					types[i],
					nullability[i],
					session,
					nonNullableTransientEntities
			);
		}
		return nonNullableTransientEntities.isEmpty() ? null : nonNullableTransientEntities;
	}
