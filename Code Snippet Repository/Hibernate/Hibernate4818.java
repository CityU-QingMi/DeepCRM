	public AbstractSchemaMigrator(
			HibernateSchemaManagementTool tool,
			SchemaFilter schemaFilter) {
		this.tool = tool;
		if ( schemaFilter == null ) {
			this.schemaFilter = DefaultSchemaFilter.INSTANCE;
		}
		else {
			this.schemaFilter = schemaFilter;
		}
	}
