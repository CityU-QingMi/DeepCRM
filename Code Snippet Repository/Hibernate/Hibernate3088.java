	@Override
	public void delete(String entityName, Object object, boolean isCascadeDeleteEnabled, Set transientEntities)
			throws HibernateException {
		checkOpenOrWaitingForAutoClose();
		if ( TRACE_ENABLED && persistenceContext.isRemovingOrphanBeforeUpates() ) {
			logRemoveOrphanBeforeUpdates( "before continuing", entityName, object );
		}
		fireDelete(
				new DeleteEvent(
						entityName,
						object,
						isCascadeDeleteEnabled,
						persistenceContext.isRemovingOrphanBeforeUpates(),
						this
				),
				transientEntities
		);
		if ( TRACE_ENABLED && persistenceContext.isRemovingOrphanBeforeUpates() ) {
			logRemoveOrphanBeforeUpdates( "after continuing", entityName, object );
		}
	}
