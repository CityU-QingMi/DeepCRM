	@Override
	public boolean map(
			SessionImplementor session,
			Map<String, Object> data,
			String[] propertyNames,
			Object[] newState,
			Object[] oldState) {
		final boolean parentDiffs = parentMapper.map( session, data, propertyNames, newState, oldState );
		final boolean mainDiffs = main.map( session, data, propertyNames, newState, oldState );

		return parentDiffs || mainDiffs;
	}
