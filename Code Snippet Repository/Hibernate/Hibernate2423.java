	private Object getNextVersion(FlushEntityEvent event) throws HibernateException {

		EntityEntry entry = event.getEntityEntry();
		EntityPersister persister = entry.getPersister();
		if ( persister.isVersioned() ) {

			Object[] values = event.getPropertyValues();

			if ( entry.isBeingReplicated() ) {
				return Versioning.getVersion( values, persister );
			}
			else {
				int[] dirtyProperties = event.getDirtyProperties();

				final boolean isVersionIncrementRequired = isVersionIncrementRequired(
						event,
						entry,
						persister,
						dirtyProperties
				);

				final Object nextVersion = isVersionIncrementRequired ?
						Versioning.increment( entry.getVersion(), persister.getVersionType(), event.getSession() ) :
						entry.getVersion(); //use the current version

				Versioning.setVersion( values, nextVersion, persister );

				return nextVersion;
			}
		}
		else {
			return null;
		}

	}
