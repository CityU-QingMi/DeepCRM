	public OverriddenMappingDefaults(
			String implicitSchemaName,
			String implicitCatalogName,
			boolean implicitlyQuoteIdentifiers,
			String implicitIdColumnName,
			String implicitTenantIdColumnName,
			String implicitDiscriminatorColumnName,
			String implicitPackageName,
			boolean autoImportEnabled,
			String implicitCascadeStyleName,
			String implicitPropertyAccessorName,
			boolean entitiesImplicitlyLazy,
			boolean pluralAttributesImplicitlyLazy,
			AccessType implicitCacheAccessType) {
		this.implicitSchemaName = implicitSchemaName;
		this.implicitCatalogName = implicitCatalogName;
		this.implicitlyQuoteIdentifiers = implicitlyQuoteIdentifiers;
		this.implicitIdColumnName = implicitIdColumnName;
		this.implicitTenantIdColumnName = implicitTenantIdColumnName;
		this.implicitDiscriminatorColumnName = implicitDiscriminatorColumnName;
		this.implicitPackageName = implicitPackageName;
		this.autoImportEnabled = autoImportEnabled;
		this.implicitCascadeStyleName = implicitCascadeStyleName;
		this.implicitPropertyAccessorName = implicitPropertyAccessorName;
		this.entitiesImplicitlyLazy = entitiesImplicitlyLazy;
		this.pluralAttributesImplicitlyLazy = pluralAttributesImplicitlyLazy;
		this.implicitCacheAccessType = implicitCacheAccessType;
	}
