	@Override
	protected void mapToMapFromObject(
			SessionImplementor session,
			Map<String, Object> idData,
			Map<String, Object> data,
			Object changed) {
		elementComponentData.getComponentMapper().mapToMapFromObject(
				session,
				idData,
				data,
				( (Map.Entry) changed ).getValue()
		);
		indexComponentData.getComponentMapper().mapToMapFromObject(
				session,
				idData,
				data,
				( (Map.Entry) changed ).getKey()
		);
	}
