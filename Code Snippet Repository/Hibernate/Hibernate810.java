		public Builder(MappingDefaults parentDefaults) {
			this.implicitSchemaName = parentDefaults.getImplicitSchemaName();
			this.implicitCatalogName = parentDefaults.getImplicitCatalogName();
			this.implicitlyQuoteIdentifiers = parentDefaults.shouldImplicitlyQuoteIdentifiers();
			this.implicitIdColumnName = parentDefaults.getImplicitIdColumnName();
			this.implicitTenantIdColumnName = parentDefaults.getImplicitTenantIdColumnName();
			this.implicitDiscriminatorColumnName = parentDefaults.getImplicitDiscriminatorColumnName();
			this.implicitPackageName = parentDefaults.getImplicitPackageName();
			this.autoImportEnabled = parentDefaults.isAutoImportEnabled();
			this.implicitCascadeStyleName = parentDefaults.getImplicitCascadeStyleName();
			this.implicitPropertyAccessorName = parentDefaults.getImplicitPropertyAccessorName();
			this.entitiesImplicitlyLazy = parentDefaults.areEntitiesImplicitlyLazy();
			this.pluralAttributesImplicitlyLazy = parentDefaults.areCollectionsImplicitlyLazy();
			this.implicitCacheAccessType = parentDefaults.getImplicitCacheAccessType();
		}
