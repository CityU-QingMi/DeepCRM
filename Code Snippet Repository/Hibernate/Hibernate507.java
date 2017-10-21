	protected AbstractEntityInsertAction(
			Serializable id,
			Object[] state,
			Object instance,
			boolean isVersionIncrementDisabled,
			EntityPersister persister,
			SharedSessionContractImplementor session) {
		super( session, id, instance, persister );
		this.state = state;
		this.isVersionIncrementDisabled = isVersionIncrementDisabled;
		this.isExecuted = false;
		this.areTransientReferencesNullified = false;

		if ( id != null ) {
			handleNaturalIdPreSaveNotifications();
		}
	}
