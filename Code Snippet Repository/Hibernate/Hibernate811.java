		public OverriddenMappingDefaults build() {
			return new OverriddenMappingDefaults(
					implicitSchemaName,
					implicitCatalogName,
					implicitlyQuoteIdentifiers,
					implicitIdColumnName,
					implicitTenantIdColumnName,
					implicitDiscriminatorColumnName,
					implicitPackageName,
					autoImportEnabled,
					implicitCascadeStyleName,
					implicitPropertyAccessorName,
					entitiesImplicitlyLazy,
					pluralAttributesImplicitlyLazy,
					implicitCacheAccessType
			);
		}
