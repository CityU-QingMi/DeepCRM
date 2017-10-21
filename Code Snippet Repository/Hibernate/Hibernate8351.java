	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) throws CallbackException {
		if ( entity instanceof Document ) {
			state[3] = state[2] = Calendar.getInstance();
			return true;
		}
		else {
			return false;
		}
	}
