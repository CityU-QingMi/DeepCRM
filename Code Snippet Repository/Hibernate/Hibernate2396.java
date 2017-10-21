	private AbstractEntityInsertAction addInsertAction(
			Object[] values,
			Serializable id,
			Object entity,
			EntityPersister persister,
			boolean useIdentityColumn,
			EventSource source,
			boolean shouldDelayIdentityInserts) {
		if ( useIdentityColumn ) {
			EntityIdentityInsertAction insert = new EntityIdentityInsertAction(
					values, entity, persister, isVersionIncrementDisabled(), source, shouldDelayIdentityInserts
			);
			source.getActionQueue().addAction( insert );
			return insert;
		}
		else {
			Object version = Versioning.getVersion( values, persister );
			EntityInsertAction insert = new EntityInsertAction(
					id, values, entity, version, persister, isVersionIncrementDisabled(), source
			);
			source.getActionQueue().addAction( insert );
			return insert;
		}
	}
