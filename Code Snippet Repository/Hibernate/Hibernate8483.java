	public Object load(
		Serializable id,
		Object optionalObject,
		LockMode lockMode,
		SharedSessionContractImplementor session
	) throws HibernateException {

		// fails when optional object is supplied

		Custom clone = null;
		Custom obj = (Custom) INSTANCES.get(id);
		if (obj!=null) {
			clone = (Custom) obj.clone();
			TwoPhaseLoad.addUninitializedEntity(
					session.generateEntityKey( id, this ),
					clone,
					this,
					LockMode.NONE,
					session
			);
			TwoPhaseLoad.postHydrate(
					this, id,
					new String[] { obj.getName() },
					null,
					clone,
					LockMode.NONE,
					session
			);
			TwoPhaseLoad.initializeEntity(
					clone,
					false,
					session,
					new PreLoadEvent( (EventSource) session )
			);
			TwoPhaseLoad.postLoad( clone, session, new PostLoadEvent( (EventSource) session ) );
		}
		return clone;
	}
