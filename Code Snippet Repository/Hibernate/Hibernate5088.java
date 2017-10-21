	public Object loadByUniqueKey(
			String entityName,
			String uniqueKeyPropertyName,
			Object key,
			SharedSessionContractImplementor session) throws HibernateException {
		final SessionFactoryImplementor factory = session.getFactory();
		UniqueKeyLoadable persister = (UniqueKeyLoadable) factory.getMetamodel().entityPersister( entityName );

		//TODO: implement 2nd level caching?! natural id caching ?! proxies?!

		EntityUniqueKey euk = new EntityUniqueKey(
				entityName,
				uniqueKeyPropertyName,
				key,
				getIdentifierOrUniqueKeyType( factory ),
				persister.getEntityMode(),
				session.getFactory()
		);

		final PersistenceContext persistenceContext = session.getPersistenceContext();
		Object result = persistenceContext.getEntity( euk );
		if ( result == null ) {
			result = persister.loadByUniqueKey( uniqueKeyPropertyName, key, session );
			
			// If the entity was not in the Persistence Context, but was found now,
			// add it to the Persistence Context
			if (result != null) {
				persistenceContext.addEntity(euk, result);
			}
		}

		return result == null ? null : persistenceContext.proxyFor( result );
	}
