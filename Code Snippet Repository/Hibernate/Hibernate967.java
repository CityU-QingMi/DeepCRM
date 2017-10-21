	public static NativeSQLQueryScalarReturn extractReturnDescription(
			JaxbHbmNativeQueryScalarReturnType rtnSource,
			HbmLocalMetadataBuildingContext context) {
		final String column = rtnSource.getColumn();
		final String typeName = rtnSource.getType();
		Type type = null;
		if ( typeName != null ) {
			type = context.getMetadataCollector().getTypeResolver().heuristicType( typeName );
			if ( type == null ) {
				throw new MappingException(
						String.format(
								"Unable to resolve type [%s] specified for native query scalar return",
								typeName
						),
						context.getOrigin()
				);
			}
		}
		return new NativeSQLQueryScalarReturn( column, type );
	}
