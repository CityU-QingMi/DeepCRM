	public void applyResultSetMapping(ResultSetMappingDefinition resultSetMappingDefinition) {
		final ResultSetMappingDefinition old = sqlResultSetMappingMap.put(
				resultSetMappingDefinition.getName(),
				resultSetMappingDefinition
		);
		if ( old != null ) {
			throw new DuplicateMappingException(
					DuplicateMappingException.Type.RESULT_SET_MAPPING,
					resultSetMappingDefinition.getName()
			);
		}
	}
