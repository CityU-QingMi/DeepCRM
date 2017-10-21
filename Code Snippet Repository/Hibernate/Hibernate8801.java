	public boolean onFlushDirty(
			Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types
	) throws CallbackException {
		if ( entity instanceof Document ) {
			currentState[3] = Calendar.getInstance();
			return true;
		}
		else {
			return false;
		}
	}
