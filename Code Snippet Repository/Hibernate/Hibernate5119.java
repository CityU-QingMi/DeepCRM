	@Override
	public Object replaceElements(
			final Object original,
			final Object target,
			final Object owner,
			final java.util.Map copyCache,
			final SharedSessionContractImplementor session) throws HibernateException {
		CollectionPersister cp = session.getFactory().getMetamodel().collectionPersister( getRole() );

		java.util.Map result = (java.util.Map) target;
		result.clear();

		for ( Object o : ( (Map) original ).entrySet() ) {
			Map.Entry me = (Map.Entry) o;
			Object key = cp.getIndexType().replace( me.getKey(), null, session, owner, copyCache );
			Object value = cp.getElementType().replace( me.getValue(), null, session, owner, copyCache );
			result.put( key, value );
		}

		return result;

	}
