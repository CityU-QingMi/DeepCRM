	public void initialize(Serializable key, SharedSessionContractImplementor session) throws HibernateException {
		LOG.debugf( "Initializing collection: %s using named query: %s", persister.getRole(), queryName );

		NativeQueryImplementor nativeQuery = session.getNamedNativeQuery( queryName );

		if ( nativeQuery.getParameterMetadata().hasNamedParameters() ) {
			nativeQuery.setParameter(
					nativeQuery.getParameterMetadata().getNamedParameterNames().iterator().next(),
					key,
					persister.getKeyType()
			);
		}
		else {
			nativeQuery.setParameter( 0, key, persister.getKeyType() );
		}

		nativeQuery.setCollectionKey( key ).setFlushMode( FlushMode.MANUAL ).list();
	}
