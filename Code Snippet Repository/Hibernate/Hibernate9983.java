	@Override
	public boolean mapToMapFromEntity(
			SessionImplementor session,
			Map<String, Object> data,
			Object newObj,
			Object oldObj) {
		final boolean parentDiffs = parentMapper.mapToMapFromEntity( session, data, newObj, oldObj );
		final boolean mainDiffs = main.mapToMapFromEntity( session, data, newObj, oldObj );

		return parentDiffs || mainDiffs;
	}
