	private static void bind(
			ResultSetMappingBindingDefinition resultSetMappingSource,
			ResultSetMappingDefinition binding,
			HbmLocalMetadataBuildingContext context) {

		int cnt = 0;

		for ( Object valueMappingSource : resultSetMappingSource.getValueMappingSources() ) {
			if ( JaxbHbmNativeQueryReturnType.class.isInstance( valueMappingSource ) ) {
				binding.addQueryReturn(
						extractReturnDescription( (JaxbHbmNativeQueryReturnType) valueMappingSource, context, cnt++ )
				);
			}
			else if ( JaxbHbmNativeQueryCollectionLoadReturnType.class.isInstance( valueMappingSource ) ) {
				binding.addQueryReturn(
						extractReturnDescription( (JaxbHbmNativeQueryCollectionLoadReturnType) valueMappingSource, context, cnt++ )
				);
			}
			else if ( JaxbHbmNativeQueryJoinReturnType.class.isInstance( valueMappingSource ) ) {
				binding.addQueryReturn(
						extractReturnDescription( (JaxbHbmNativeQueryJoinReturnType) valueMappingSource, context, cnt++ )
				);
			}
			else if ( JaxbHbmNativeQueryScalarReturnType.class.isInstance( valueMappingSource ) ) {
				binding.addQueryReturn(
						extractReturnDescription( (JaxbHbmNativeQueryScalarReturnType) valueMappingSource, context )
				);
			}
		}
	}
