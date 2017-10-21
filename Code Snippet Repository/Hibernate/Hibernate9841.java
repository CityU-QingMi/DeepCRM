	protected String getSchema(String schemaFromAnnotation, Table table) {
		// Get the schema from the annotation ...
		String schema = schemaFromAnnotation;
		// ... if empty, try using the default ...
		if ( StringTools.isEmpty( schema ) ) {
			schema = globalCfg.getDefaultSchemaName();

			// ... if still empty, use the same as the normal table.
			if ( StringTools.isEmpty( schema ) ) {
				schema = table.getSchema();
			}
		}

		return schema;
	}
