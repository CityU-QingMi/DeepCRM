	@Override
	public void execute() throws HibernateException {
		nullifyTransientReferencesIfNotAlready();

		final EntityPersister persister = getPersister();
		final SharedSessionContractImplementor session = getSession();
		final Object instance = getInstance();

		final boolean veto = preInsert();

		// Don't need to lock the cache here, since if someone
		// else inserted the same pk first, the insert would fail

		if ( !veto ) {
			generatedId = persister.insert( getState(), instance, session );
			if ( persister.hasInsertGeneratedProperties() ) {
				persister.processInsertGeneratedProperties( generatedId, instance, getState(), session );
			}
			//need to do that here rather than in the save event listener to let
			//the post insert events to have a id-filled entity when IDENTITY is used (EJB3)
			persister.setIdentifier( instance, generatedId, session );
			session.getPersistenceContext().registerInsertedKey( getPersister(), generatedId );
			entityKey = session.generateEntityKey( generatedId, persister );
			session.getPersistenceContext().checkUniqueness( entityKey, getInstance() );
		}


		//TODO: this bit actually has to be called after all cascades!
		//      but since identity insert is called *synchronously*,
		//      instead of asynchronously as other actions, it isn't
/**/
/**/
/**/
/**/

		postInsert();

		if ( session.getFactory().getStatistics().isStatisticsEnabled() && !veto ) {
			session.getFactory().getStatisticsImplementor().insertEntity( getPersister().getEntityName() );
		}

		markExecuted();
	}
