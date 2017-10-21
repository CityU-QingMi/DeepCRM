	private void logIfEmptyCompositesEnabled(Map<String, Object> props ) {
		final boolean isEmptyCompositesEnabled = ConfigurationHelper.getBoolean(
				AvailableSettings.CREATE_EMPTY_COMPOSITES_ENABLED,
				props,
				false
		);
		if ( isEmptyCompositesEnabled ) {
			// It would be nice to do this logging in ComponentMetamodel, where
			// AvailableSettings.CREATE_EMPTY_COMPOSITES_ENABLED is actually used.
			// Unfortunately that would end up logging a message several times for
			// each embeddable/composite. Doing it here will log the message only
			// once.
			LOG.emptyCompositesEnabled();
		}
	}
