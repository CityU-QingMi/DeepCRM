	@Override
	public void lock(Serializable id, Object version, Object object, int timeout, SharedSessionContractImplementor session) {
		final String sql = determineSql( timeout );
		final SessionFactoryImplementor factory = session.getFactory();
		try {
			try {
				final PreparedStatement st = session.getJdbcCoordinator().getStatementPreparer().prepareStatement( sql );
				try {
					getLockable().getIdentifierType().nullSafeSet( st, id, 1, session );
					if ( getLockable().isVersioned() ) {
						getLockable().getVersionType().nullSafeSet(
								st,
								version,
								getLockable().getIdentifierType().getColumnSpan( factory ) + 1,
								session
						);
					}

					final ResultSet rs = session.getJdbcCoordinator().getResultSetReturn().extract( st );
					try {
						if ( !rs.next() ) {
							if ( factory.getStatistics().isStatisticsEnabled() ) {
								factory.getStatistics().optimisticFailure( getLockable().getEntityName() );
							}
							throw new StaleObjectStateException( getLockable().getEntityName(), id );
						}
					}
					finally {
						session.getJdbcCoordinator().getLogicalConnection().getResourceRegistry().release( rs, st );
					}
				}
				finally {
					session.getJdbcCoordinator().getLogicalConnection().getResourceRegistry().release( st );
					session.getJdbcCoordinator().afterStatementExecution();
				}

			}
			catch ( SQLException e ) {
				throw session.getJdbcServices().getSqlExceptionHelper().convert(
						e,
						"could not lock: " + MessageHelper.infoString( getLockable(), id, session.getFactory() ),
						sql
				);
			}
		}
		catch (JDBCException e) {
			throw new PessimisticEntityLockException( object, "could not obtain pessimistic lock", e );
		}
	}
