	@Override
	@SuppressWarnings("")
	public boolean needsUpdating(Object entry, int i, Type elemType) throws HibernateException {
		final Map sn = (Map) getSnapshot();
		final Map.Entry e = (Map.Entry) entry;
		final Object snValue = sn.get( e.getKey() );
		return e.getValue() != null
				&& snValue != null
				&& elemType.isDirty( snValue, e.getValue(), getSession() );
	}
