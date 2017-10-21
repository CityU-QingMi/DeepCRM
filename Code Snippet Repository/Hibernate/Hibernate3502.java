	private void checkVersion(
			final int i,
			final Loadable persister,
			final Serializable id,
			final Object entity,
			final ResultSet rs,
			final SharedSessionContractImplementor session) throws HibernateException, SQLException {

		Object version = session.getPersistenceContext().getEntry( entity ).getVersion();

		if ( version != null ) { //null version means the object is in the process of being loaded somewhere else in the ResultSet
			final VersionType versionType = persister.getVersionType();
			final Object currentVersion = versionType.nullSafeGet(
					rs,
					getEntityAliases()[i].getSuffixedVersionAliases(),
					session,
					null
			);
			if ( !versionType.isEqual( version, currentVersion ) ) {
				if ( session.getFactory().getStatistics().isStatisticsEnabled() ) {
					session.getFactory().getStatistics().optimisticFailure( persister.getEntityName() );
				}
				throw new StaleObjectStateException( persister.getEntityName(), id );
			}
		}

	}
