	public static void bindSqlResultSetMappings(
			SqlResultSetMappings ann,
			MetadataBuildingContext context,
			boolean isDefault) {
		if ( ann == null ) {
			return;
		}

		for (SqlResultSetMapping rs : ann.value()) {
			//no need to handle inSecondPass
			context.getMetadataCollector().addSecondPass( new ResultsetMappingSecondPass( rs, context, true ) );
		}
	}
