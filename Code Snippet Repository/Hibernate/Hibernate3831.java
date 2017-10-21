	private void checkVersion(
			SharedSessionContractImplementor session,
			ResultSet resultSet,
			EntityPersister persister,
			EntityAliases entityAliases,
			EntityKey entityKey,
			Object entityInstance) {
		final Object version = session.getPersistenceContext().getEntry( entityInstance ).getVersion();

		if ( version != null ) {
			//null version means the object is in the process of being loaded somewhere else in the ResultSet
			VersionType versionType = persister.getVersionType();
			final Object currentVersion;
			try {
				currentVersion = versionType.nullSafeGet(
						resultSet,
						entityAliases.getSuffixedVersionAliases(),
						session,
						null
				);
			}
			catch (SQLException e) {
				throw session.getFactory().getServiceRegistry().getService( JdbcServices.class ).getSqlExceptionHelper().convert(
						e,
						"Could not read version value from result set"
				);
			}

			if ( !versionType.isEqual( version, currentVersion ) ) {
				if ( session.getFactory().getStatistics().isStatisticsEnabled() ) {
					session.getFactory().getStatistics().optimisticFailure( persister.getEntityName() );
				}
				throw new StaleObjectStateException( persister.getEntityName(), entityKey.getIdentifier() );
			}
		}
	}
