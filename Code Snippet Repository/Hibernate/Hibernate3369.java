	protected void populate(
			MetadataBuilder metamodelBuilder,
			MergedSettings mergedSettings,
			StandardServiceRegistry ssr,
			List<AttributeConverterDefinition> attributeConverterDefinitions) {
		if ( persistenceUnit.getTempClassLoader() != null ) {
			metamodelBuilder.applyTempClassLoader( persistenceUnit.getTempClassLoader() );
		}

		metamodelBuilder.applyScanEnvironment( new StandardJpaScanEnvironmentImpl( persistenceUnit ) );
		metamodelBuilder.applyScanOptions(
				new StandardScanOptions(
						(String) configurationValues.get( org.hibernate.cfg.AvailableSettings.SCANNER_DISCOVERY ),
						persistenceUnit.isExcludeUnlistedClasses()
				)
		);

		if ( mergedSettings.cacheRegionDefinitions != null ) {
			for ( CacheRegionDefinition localCacheRegionDefinition : mergedSettings.cacheRegionDefinitions ) {
				metamodelBuilder.applyCacheRegionDefinition( localCacheRegionDefinition );
			}
		}

		final TypeContributorList typeContributorList = (TypeContributorList) configurationValues.remove(
				TYPE_CONTRIBUTORS
		);
		if ( typeContributorList != null ) {
			for ( TypeContributor typeContributor : typeContributorList.getTypeContributors() ) {
				metamodelBuilder.applyTypes( typeContributor );
			}
		}

		if ( attributeConverterDefinitions != null ) {
			for ( AttributeConverterDefinition attributeConverterDefinition : attributeConverterDefinitions ) {
				metamodelBuilder.applyAttributeConverter( attributeConverterDefinition );
			}
		}
	}
