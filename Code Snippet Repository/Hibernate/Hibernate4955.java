	@Override
	public Object[] getPropertyValuesToInsert(Object entity, Map mergeMap, SharedSessionContractImplementor session) {
		final int span = entityMetamodel.getPropertySpan();
		final Object[] result = new Object[span];

		for ( int j = 0; j < span; j++ ) {
			result[j] = getters[j].getForInsert( entity, mergeMap, session );
		}
		return result;
	}
