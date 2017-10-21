	@Override
	@SuppressWarnings({""})
	protected void mapToMapFromObject(
			SessionImplementor session,
			Map<String, Object> idData,
			Map<String, Object> data,
			Object changed) {
		final Pair<Integer, Object> indexValuePair = (Pair<Integer, Object>) changed;
		elementComponentData.getComponentMapper().mapToMapFromObject(
				session,
				idData,
				data,
				indexValuePair.getSecond()
		);
		indexComponentData.getComponentMapper().mapToMapFromObject( session, idData, data, indexValuePair.getFirst() );
	}
