	public boolean onSave(
			Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types
	) throws CallbackException {
		if ( entity instanceof Document ) {
			state[4] = state[3] = Calendar.getInstance();
			return true;
		}
		else {
			return false;
		}
	}
