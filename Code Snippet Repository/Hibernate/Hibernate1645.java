	@Override
	public final Session currentSession() throws HibernateException {
		Session current = existingSession( factory() );
		if ( current == null ) {
			current = buildOrObtainSession();
			// register a cleanup sync
			current.getTransaction().registerSynchronization( buildCleanupSynch() );
			// wrap the session in the transaction-protection proxy
			if ( needsWrapping( current ) ) {
				current = wrap( current );
			}
			// then bind it
			doBind( current, factory() );
		}
		else {
			validateExistingSession( current );
		}
		return current;
	}
