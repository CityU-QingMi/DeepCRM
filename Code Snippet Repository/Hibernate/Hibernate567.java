	@Override
	public void doBeforeTransactionCompletion(SessionImplementor session) {
		final EntityPersister persister = entry.getPersister();

		if ( !entry.isExistsInDatabase() ) {
			// HHH-9419: We cannot check for a version of an entry we ourselves deleted
			return;
		}

		final Object latestVersion = persister.getCurrentVersion( entry.getId(), session );
		if ( !entry.getVersion().equals( latestVersion ) ) {
			throw new OptimisticLockException(
					object,
					"Newer version [" + latestVersion +
							"] of entity [" + MessageHelper.infoString( entry.getEntityName(), entry.getId() ) +
							"] found in database"
			);
		}
	}
