	protected void handleNaturalIdPreSaveNotifications() {
		// before save, we need to add a local (transactional) natural id cross-reference
		getSession().getPersistenceContext().getNaturalIdHelper().manageLocalNaturalIdCrossReference(
				getPersister(),
				getId(),
				state,
				null,
				CachedNaturalIdValueSource.INSERT
		);
	}
