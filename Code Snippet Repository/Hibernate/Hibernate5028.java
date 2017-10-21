	@Override
	public boolean isDirty(Object old, Object current, SharedSessionContractImplementor session)
			throws HibernateException {

		// collections don't dirty an unversioned parent entity

		// TODO: I don't really like this implementation; it would be better if
		// this was handled by searchForDirtyCollections()
		return super.isDirty( old, current, session );
		// return false;

	}
