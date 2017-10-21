	@SuppressWarnings("")
	@Nullable
	protected FlushMode prepareFlushMode(Session session, boolean readOnly) throws PersistenceException {
		FlushMode flushMode = (FlushMode) ReflectionUtils.invokeMethod(getFlushMode, session);
		Assert.state(flushMode != null, "No FlushMode from Session");
		if (readOnly) {
			// We should suppress flushing for a read-only transaction.
			if (!flushMode.equals(FlushMode.MANUAL)) {
				session.setFlushMode(FlushMode.MANUAL);
				return flushMode;
			}
		}
		else {
			// We need AUTO or COMMIT for a non-read-only transaction.
			if (flushMode.lessThan(FlushMode.COMMIT)) {
				session.setFlushMode(FlushMode.AUTO);
				return flushMode;
			}
		}
		// No FlushMode change needed...
		return null;
	}
